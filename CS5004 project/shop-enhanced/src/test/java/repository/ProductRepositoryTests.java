package com.example.shop.repository;

import com.example.shop.model.Category;
import com.example.shop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ProductRepository.
 * Tests CRUD operations, search, filter, and auto-ID generation.
 * 
 * @author Patrick
 * @version 1.0
 */
// DESIGN PRINCIPLE: Testing - comprehensive tests ensure code quality
public class ProductRepositoryTests {

  private ProductRepository repo;

  /**
   * Sets up fresh repository before each test.
   */
  @BeforeEach
  public void setup() {
    repo = new ProductRepository();
  }

  // ===== BASIC CRUD TESTS =====

  /**
   * Tests finding all products.
   */
  @Test
  public void testFindAll() {
    List<Product> products = repo.findAll();
    assertEquals(4, products.size(), "Repository should start with 4 products");
  }

  /**
   * Tests finding product by ID when it exists.
   */
  @Test
  public void testFindById_ProductExists() {
    Product product = repo.findById(1);
    
    assertNotNull(product, "Product with ID 1 should exist");
    assertEquals("Blue T-Shirt", product.getName(), "Product name should match");
  }

  /**
   * Tests finding product by ID when it doesn't exist.
   */
  @Test
  public void testFindById_ProductDoesNotExist() {
    Product product = repo.findById(999);
    assertNull(product, "Non-existent product should return null");
  }

  /**
   * Tests updating a product.
   */
  @Test
  public void testUpdate() {
    Product updated = new Product(1, "Red T-Shirt", 24.99, Category.CLOTHES, 
        "Updated description", 10);
    
    repo.update(updated);
    
    Product retrieved = repo.findById(1);
    assertEquals("Red T-Shirt", retrieved.getName(), "Name should be updated");
    assertEquals(24.99, retrieved.getPrice(), "Price should be updated");
    assertEquals("Updated description", retrieved.getDescription(), "Description should be updated");
    assertEquals(10, retrieved.getStock(), "Stock should be updated");
  }

  /**
   * Tests deleting a product.
   */
  @Test
  public void testDelete() {
    assertNotNull(repo.findById(1), "Product should exist before deletion");
    
    repo.deleteById(1);
    
    assertNull(repo.findById(1), "Product should be null after deletion");
  }

  // ===== AUTO-ID GENERATION TESTS =====

  /**
   * Tests that new products get auto-generated IDs.
   */
  @Test
  public void testAutoIdGeneration_FirstNewProduct() {
    Product product = repo.save("New Product", 49.99, Category.TOYS, 
        "Brand new product", 25);
    
    assertNotNull(product, "Saved product should not be null");
    assertEquals(5, product.getId(), "First new product should have ID 5");
    
    Product retrieved = repo.findById(5);
    assertNotNull(retrieved, "Should be able to find product by auto-generated ID");
    assertEquals("New Product", retrieved.getName(), "Retrieved product should have correct name");
  }

  /**
   * Tests that multiple products get unique sequential IDs.
   */
  @Test
  public void testAutoIdGeneration_MultipleProducts() {
    Product p1 = repo.save("Product 1", 10.00, Category.BOOKS, "First", 5);
    Product p2 = repo.save("Product 2", 20.00, Category.BOOKS, "Second", 10);
    Product p3 = repo.save("Product 3", 30.00, Category.BOOKS, "Third", 15);
    
    assertEquals(5, p1.getId(), "First product should have ID 5");
    assertEquals(6, p2.getId(), "Second product should have ID 6");
    assertEquals(7, p3.getId(), "Third product should have ID 7");
    
    assertNotNull(repo.findById(5));
    assertNotNull(repo.findById(6));
    assertNotNull(repo.findById(7));
  }

  // ===== SEARCH & FILTER TESTS =====

  /**
   * Tests search by name finds matches.
   */
  @Test
  public void testSearchByName_FindsMatches() {
    List<Product> results = repo.searchByName("shirt");
    
    assertEquals(1, results.size(), "Should find 1 product with 'shirt' in name");
    assertEquals("Blue T-Shirt", results.get(0).getName());
  }

  /**
   * Tests search is case-insensitive.
   */
  @Test
  public void testSearchByName_CaseInsensitive() {
    List<Product> results1 = repo.searchByName("SHIRT");
    List<Product> results2 = repo.searchByName("Shirt");
    List<Product> results3 = repo.searchByName("shirt");
    
    assertEquals(1, results1.size());
    assertEquals(1, results2.size());
    assertEquals(1, results3.size());
  }

  /**
   * Tests search returns empty list when no matches.
   */
  @Test
  public void testSearchByName_NoMatches() {
    List<Product> results = repo.searchByName("nonexistent");
    
    assertNotNull(results, "Should return list, not null");
    assertEquals(0, results.size(), "Should find no matches");
  }

  /**
   * Tests empty search returns all products.
   */
  @Test
  public void testSearchByName_EmptyQuery() {
    List<Product> results1 = repo.searchByName("");
    assertEquals(4, results1.size(), "Empty search should return all products");
    
    List<Product> results2 = repo.searchByName(null);
    assertEquals(4, results2.size(), "Null search should return all products");
  }

  /**
   * Tests filter by category.
   */
  @Test
  public void testFindByCategory() {
    List<Product> results = repo.findByCategory(Category.CLOTHES);
    
    assertEquals(1, results.size(), "Should find 1 CLOTHES product");
    assertEquals(Category.CLOTHES, results.get(0).getCategory());
  }

  /**
   * Tests null category returns all products.
   */
  @Test
  public void testFindByCategory_Null() {
    List<Product> results = repo.findByCategory(null);
    assertEquals(4, results.size(), "Null category should return all products");
  }

  /**
   * Tests combined search and filter.
   */
  @Test
  public void testSearchAndFilter_BothParameters() {
    repo.save("Gaming Mouse", 59.99, Category.ELECTRONICS, "RGB mouse", 15);
    
    List<Product> results = repo.searchAndFilter("Gaming", Category.ELECTRONICS);
    
    assertTrue(results.size() >= 1, "Should find at least 1 matching product");
    
    for (Product p : results) {
      assertEquals(Category.ELECTRONICS, p.getCategory());
      assertTrue(p.getName().toLowerCase().contains("gaming"));
    }
  }

  /**
   * Tests search without category filter.
   */
  @Test
  public void testSearchAndFilter_OnlySearchQuery() {
    List<Product> results = repo.searchAndFilter("Laptop", null);
    
    assertEquals(1, results.size());
    assertTrue(results.get(0).getName().contains("Laptop"));
  }

  /**
   * Tests filter without search query.
   */
  @Test
  public void testSearchAndFilter_OnlyCategoryFilter() {
    List<Product> results = repo.searchAndFilter(null, Category.FOOD);
    
    assertEquals(1, results.size());
    assertEquals(Category.FOOD, results.get(0).getCategory());
  }

  // ===== DESCRIPTION AND STOCK TESTS =====

  /**
   * Tests saving and retrieving description.
   */
  @Test
  public void testProductWithDescription() {
    Product product = repo.save("Test Product", 19.99, Category.TOYS,
        "This is a detailed description", 10);
    
    Product retrieved = repo.findById(product.getId());
    assertEquals("This is a detailed description", retrieved.getDescription());
  }

  /**
   * Tests saving and retrieving stock.
   */
  @Test
  public void testProductWithStock() {
    Product product = repo.save("Test Product", 19.99, Category.TOYS,
        "Description", 42);
    
    Product retrieved = repo.findById(product.getId());
    assertEquals(42, retrieved.getStock());
  }

  /**
   * Tests stock helper methods.
   */
  @Test
  public void testStockHelperMethods() {
    Product outOfStock = repo.save("Out", 10.00, Category.TOYS, "None", 0);
    Product lowStock = repo.save("Low", 10.00, Category.TOYS, "Few", 3);
    Product goodStock = repo.save("Good", 10.00, Category.TOYS, "Many", 20);
    
    assertFalse(outOfStock.isInStock(), "Out of stock should return false");
    assertTrue(lowStock.isInStock(), "Low stock should return true");
    assertTrue(goodStock.isInStock(), "Good stock should return true");
    
    assertFalse(outOfStock.isLowStock(), "Out of stock is not low stock");
    assertTrue(lowStock.isLowStock(), "Low stock should return true");
    assertFalse(goodStock.isLowStock(), "Good stock is not low stock");
  }

  // ===== UTILITY METHOD TESTS =====

  /**
   * Tests count method.
   */
  @Test
  public void testCount() {
    assertEquals(4, repo.count());
    
    repo.save("New", 10.00, Category.BOOKS, "Test", 5);
    assertEquals(5, repo.count());
    
    repo.deleteById(1);
    assertEquals(4, repo.count());
  }

  /**
   * Tests existsById method.
   */
  @Test
  public void testExistsById() {
    assertTrue(repo.existsById(1), "Product 1 should exist");
    assertFalse(repo.existsById(999), "Product 999 should not exist");
    
    Product product = repo.save("New", 10.00, Category.BOOKS, "Test", 5);
    assertTrue(repo.existsById(product.getId()), "Newly added product should exist");
    
    repo.deleteById(product.getId());
    assertFalse(repo.existsById(product.getId()), "Deleted product should not exist");
  }
}
