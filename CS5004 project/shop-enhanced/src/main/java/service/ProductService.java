package com.example.shop.service;

import com.example.shop.model.Product;
import com.example.shop.model.Category;
import com.example.shop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for product business logic.
 * Acts as intermediary between Controller and Repository.
 * 
 *
 *
 */
// DESIGN PRINCIPLE: Separation of Concerns - business logic separate from web and data layers
@Service
public class ProductService {

  // DESIGN PRINCIPLE: Dependency Injection - Spring provides the repository
  private final ProductRepository repo;

  /**
   * Constructor with dependency injection.
   * 
   * @param repo ProductRepository to use for data access
   */
  // DESIGN PRINCIPLE: Composition over Inheritance - uses HAS-A relationship
  public ProductService(ProductRepository repo) {
    this.repo = repo;
  }

  /**
   * Retrieves all products.
   * 
   * @return List of all products
   */
  public List<Product> getAllProducts() {
    return repo.findAll();
  }

  /**
   * Gets a product by ID.
   * 
   * @param id Product ID
   * @return Product if found, null otherwise
   */
  public Product getProductById(int id) {
    return repo.findById(id);
  }

  /**
   * Adds a new product with auto-generated ID.
   * 
   * @param name Product name
   * @param price Product price
   * @param category Product category
   * @param description Product description
   * @param stock Stock quantity
   * @return The newly created product
   */
  public Product addProduct(String name, double price, Category category, 
                            String description, int stock) {
    return repo.save(name, price, category, description, stock);
  }

  /**
   * Updates an existing product.
   * 
   * @param product Product with updated information
   */
  public void updateProduct(Product product) {
    repo.update(product);
  }
  
  /**
   * Updates an existing product using individual parameters.
   * 
   * @param id Product ID
   * @param name New name
   * @param price New price
   * @param category New category
   * @param description New description
   * @param stock New stock quantity
   */
  public void updateProduct(int id, String name, double price, Category category,
                           String description, int stock) {
    Product product = new Product(id, name, price, category, description, stock);
    repo.update(product);
  }

  /**
   * Deletes a product by ID.
   * 
   * @param id Product ID to delete
   */
  public void deleteProduct(int id) {
    repo.deleteById(id);
  }

  /**
   * Searches products by name.
   * 
   * @param query Search term
   * @return List of matching products
   */
  public List<Product> searchProducts(String query) {
    return repo.searchByName(query);
  }

  /**
   * Filters products by category.
   * 
   * @param category Category to filter by
   * @return List of products in that category
   */
  public List<Product> filterByCategory(Category category) {
    return repo.findByCategory(category);
  }

  /**
   * Searches and filters products.
   * 
   * @param query Search term (can be null)
   * @param category Category filter (can be null)
   * @return List of matching products
   */
  public List<Product> searchAndFilter(String query, Category category) {
    return repo.searchAndFilter(query, category);
  }

  /**
   * Gets total product count.
   * 
   * @return Number of products
   */
  public int getProductCount() {
    return repo.count();
  }

  /**
   * Checks if product exists.
   * 
   * @param id Product ID to check
   * @return true if exists, false otherwise
   */
  public boolean productExists(int id) {
    return repo.existsById(id);
  }
  
  /**
   * Gets all available categories.
   * 
   * @return Array of all categories
   */
  public Category[] getAllCategories() {
    return Category.values();
  }
}
