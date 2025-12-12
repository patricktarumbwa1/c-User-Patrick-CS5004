package com.example.shop.service;

import com.example.shop.model.Category;
import com.example.shop.model.Product;
import com.example.shop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ProductService.
 * Tests business logic layer operations.
 * 
 *
 *
 */
public class ProductServiceTests {

  private ProductService service;

  /**
   * Sets up fresh service before each test.
   */
  @BeforeEach
  public void setup() {
    ProductRepository repo = new ProductRepository();
    service = new ProductService(repo);
  }

  // ===== BASIC CRUD TESTS =====

  /**
   * Tests getting all products.
   */
  @Test
  public void testGetAllProducts() {
    List<Product> products = service.getAllProducts();
    assertEquals(4, products.size(), "Should return 4 products");
  }

  /**
   * Tests getting product by ID when it exists.
   */
  @Test
  public void testGetProductById_Exists() {
    Product product = service.getProductById(2);
    
    assertNotNull(product, "Product should exist");
    assertEquals("Gaming Laptop", product.getName());
    assertEquals(Category.ELECTRONICS, product.getCategory());
  }

  /**
   * Tests getting product by ID when it doesn't exist.
   */
  @Test
  public void testGetProductById_DoesNotExist() {
    Product product = service.getProductById(999);
    assertNull(product, "Non-existent product should return null");
  }

  /**
   * Tests deleting a product.
   */
  @Test
  public void testDeleteProduct() {
    assertNotNull(service.getProductById(1));
    
    service.deleteProduct(1);
    
    assertNull(service.getProductById(1));
  }

  // ===== AUTO-ID GENERATION TESTS =====

  /**
   * Tests adding product with auto-generated ID.
   */
  @Test
  public void testAddProduct_AutoGeneratesId() {
    Product newProduct = service.addProduct(
        "New Gadget",
        99.99,
        Category.ELECTRONICS,
        "Cool new gadget",
        15
    );
    
    assertNotNull(newProduct, "Service should return the created product");
    assertTrue(newProduct.getId() > 0, "Product should have a positive ID");
    
    Product retrieved = service.getProductById(newProduct.getId());
    assertNotNull(retrieved, "Should be able to retrieve by auto-generated ID");
    assertEquals("New Gadget", retrieved.getName());
    assertEquals(99.99, retrieved.getPrice());
    assertEquals("Cool new gadget", retrieved.getDescription());
    assertEquals(15, retrieved.getStock());
  }

  /**
   * Tests multiple products get unique IDs.
   */
  @Test
  public void testAddProduct_UniqueIds() {
    Product p1 = service.addProduct("Product 1", 10.0, Category.BOOKS, "First", 5);
    Product p2 = service.addProduct("Product 2", 20.0, Category.BOOKS, "Second", 10);
    Product p3 = service.addProduct("Product 3", 30.0, Category.BOOKS, "Third", 15);
    
    assertNotEquals(p1.getId(), p2.getId(), "Products should have different IDs");
    assertNotEquals(p2.getId(), p3.getId(), "Products should have different IDs");
    assertNotEquals(p1.getId(), p3.getId(), "Products should have different IDs");
    
    assertEquals(p1.getId() + 1, p2.getId(), "IDs should increment");
    assertEquals(p2.getId() + 1, p3.getId(), "IDs should increment");
  }

  // ===== UPDATE TESTS =====

  /**
   * Tests updating product with Product object.
   */
  @Test
  public void testUpdateProduct_WithProductObject() {
    Product updated = new Product(
        1,
        "Updated Name",
        29.99,
        Category.CLOTHES,
        "Updated description",
        20
    );
    
    service.updateProduct(updated);
    
    Product retrieved = service.getProductById(1);
    assertEquals("Updated Name", retrieved.getName());
    assertEquals(29.99, retrieved.getPrice());
    assertEquals("Updated description", retrieved.getDescription());
    assertEquals(20, retrieved.getStock());
  }

  /**
   * Tests updating product with individual parameters.
   */
  @Test
  public void testUpdateProduct_WithParameters() {
    service.updateProduct(
        2,
        "Updated Laptop",
        1099.99,
        Category.ELECTRONICS,
        "Updated gaming laptop",
        3
    );
    
    Product retrieved = service.getProductById(2);
    assertEquals("Updated Laptop", retrieved.getName());
    assertEquals(1099.99, retrieved.getPrice());
    assertEquals("Updated gaming laptop", retrieved.getDescription());
    assertEquals(3, retrieved.getStock());
  }

  // ===== SEARCH & FILTER TESTS =====

  /**
   * Tests searching products by name.
   */
  @Test
  public void testSearchProducts() {
    List<Product> results = service.searchProducts("Laptop");
    
    assertTrue(results.size() > 0, "Should find matching products");
    
    boolean found = results.stream()
        .anyMatch(p -> p.getName().toLowerCase().contains("laptop"));
    assertTrue(found, "Results should contain 'laptop'");
  }

  /**
   * Tests search with no matches.
   */
  @Test
  public void testSearchProducts_NoMatches() {
    List<Product> results = service.searchProducts("nonexistent");
    
    assertNotNull(results, "Should return list, not null");
    assertEquals(0, results.size(), "Should find no matches");
  }

  /**
   * Tests filtering by category.
   */
  @Test
  public void testFilterByCategory() {
    List<Product> results = service.filterByCategory(Category.ELECTRONICS);
    
    assertTrue(results.size() > 0, "Should find electronics");
    
    for (Product p : results) {
      assertEquals(Category.ELECTRONICS, p.getCategory());
    }
  }

  /**
   * Tests combined search and filter.
   */
  @Test
  public void testSearchAndFilter() {
    service.addProduct("Gaming Mouse", 49.99, Category.ELECTRONICS, 
        "RGB gaming mouse", 10);
    
    List<Product> results = service.searchAndFilter("Gaming", Category.ELECTRONICS);
    
    assertTrue(results.size() > 0, "Should find matching products");
    
    for (Product p : results) {
      assertEquals(Category.ELECTRONICS, p.getCategory(), "Should be electronics");
      assertTrue(p.getName().toLowerCase().contains("gaming"), "Name should contain 'gaming'");
    }
  }

  /**
   * Tests search and filter with null parameters.
   */
  @Test
  public void testSearchAndFilter_NullParameters() {
    List<Product> results1 = service.searchAndFilter("Chocolate", null);
    assertTrue(results1.size() > 0);
    
    List<Product> results2 = service.searchAndFilter(null, Category.FOOD);
    assertTrue(results2.size() > 0);
    
    List<Product> results3 = service.searchAndFilter(null, null);
    assertEquals(4, results3.size(), "Both null should return all products");
  }

  // ===== DESCRIPTION AND STOCK TESTS =====

  /**
   * Tests adding product with description.
   */
  @Test
  public void testAddProduct_WithDescription() {
    Product product = service.addProduct(
        "Test Product",
        19.99,
        Category.TOYS,
        "This is a test description with details",
        10
    );
    
    Product retrieved = service.getProductById(product.getId());
    assertEquals("This is a test description with details", retrieved.getDescription());
  }

  /**
   * Tests adding product with stock.
   */
  @Test
  public void testAddProduct_WithStock() {
    Product product = service.addProduct(
        "Test Product",
        19.99,
        Category.TOYS,
        "Description",
        42
    );
    
    Product retrieved = service.getProductById(product.getId());
    assertEquals(42, retrieved.getStock());
  }

  /**
   * Tests updating product description and stock.
   */
  @Test
  public void testUpdateProduct_DescriptionAndStock() {
    service.updateProduct(
        1,
        "Blue T-Shirt",
        19.99,
        Category.CLOTHES,
        "NEW: Updated description with more details",
        100
    );
    
    Product retrieved = service.getProductById(1);
    assertEquals("NEW: Updated description with more details", retrieved.getDescription());
    assertEquals(100, retrieved.getStock());
  }

  /**
   * Tests stock status helper methods.
   */
  @Test
  public void testStockStatusMethods() {
    Product outOfStock = service.addProduct("Out", 10.0, Category.TOYS, "None", 0);
    Product lowStock = service.addProduct("Low", 10.0, Category.TOYS, "Few", 2);
    Product goodStock = service.addProduct("Good", 10.0, Category.TOYS, "Many", 50);
    
    Product out = service.getProductById(outOfStock.getId());
    Product low = service.getProductById(lowStock.getId());
    Product good = service.getProductById(goodStock.getId());
    
    assertFalse(out.isInStock(), "0 stock = out of stock");
    assertTrue(low.isInStock(), "2 stock = in stock");
    assertTrue(good.isInStock(), "50 stock = in stock");
    
    assertFalse(out.isLowStock(), "0 is not low stock");
    assertTrue(low.isLowStock(), "2 is low stock");
    assertFalse(good.isLowStock(), "50 is not low stock");
  }

  // ===== UTILITY METHOD TESTS =====

  /**
   * Tests product count.
   */
  @Test
  public void testGetProductCount() {
    assertEquals(4, service.getProductCount());
    
    service.addProduct("New", 10.0, Category.BOOKS, "Test", 5);
    assertEquals(5, service.getProductCount());
    
    service.deleteProduct(1);
    assertEquals(4, service.getProductCount());
  }

  /**
   * Tests checking if product exists.
   */
  @Test
  public void testProductExists() {
    assertTrue(service.productExists(1), "Product 1 should exist");
    assertFalse(service.productExists(999), "Product 999 should not exist");
  }

  /**
   * Tests getting all categories.
   */
  @Test
  public void testGetAllCategories() {
    Category[] categories = service.getAllCategories();
    
    assertNotNull(categories, "Should return categories array");
    assertEquals(5, categories.length, "Should have 5 categories");
    
    assertTrue(contains(categories, Category.CLOTHES));
    assertTrue(contains(categories, Category.ELECTRONICS));
    assertTrue(contains(categories, Category.FOOD));
    assertTrue(contains(categories, Category.TOYS));
    assertTrue(contains(categories, Category.BOOKS));
  }

  /**
   * Helper method to check if array contains value.
   * 
   * @param array Array to search
   * @param value Value to find
   * @return true if found, false otherwise
   */
  private boolean contains(Category[] array, Category value) {
    for (Category c : array) {
      if (c == value) return true;
    }
    return false;
  }
}
