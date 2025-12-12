package com.example.shop.repository;

import com.example.shop.model.Product;
import com.example.shop.model.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repository for managing product data storage.
 * Uses in-memory ArrayList to store products.
 * Provides CRUD operations and search/filter functionality.
 * 
 *
 *
 */
// DESIGN PRINCIPLE: Single Responsibility - only handles data storage and retrieval
@Repository
public class ProductRepository {

  // DESIGN PRINCIPLE: Appropriate Data Structures - ArrayList for dynamic storage
  private List<Product> products = new ArrayList<>();
  
  // DESIGN PRINCIPLE: Auto-ID Generation - prevents duplicate ID errors
  private int nextId = 1;

  /**
   * Constructor that initializes repository with sample products.
   */
  public ProductRepository() {
    products.add(new Product(nextId++, "Blue T-Shirt", 19.99, Category.CLOTHES, 
        "Comfortable cotton t-shirt in blue", 15));
    products.add(new Product(nextId++, "Gaming Laptop", 999.99, Category.ELECTRONICS, 
        "High-performance laptop for gaming and work", 5));
    products.add(new Product(nextId++, "Chocolate Bar", 2.49, Category.FOOD, 
        "Delicious dark chocolate bar", 50));
    products.add(new Product(nextId++, "Action Figure", 24.99, Category.TOYS, 
        "Superhero action figure with accessories", 20));
  }

  /**
   * Generates next unique product ID.
   * 
   * @return Next available ID
   */
  private int getNextId() {
    return nextId++;
  }

  /**
   * Retrieves all products.
   * 
   * @return List of all products
   */
  public List<Product> findAll() {
    return products;
  }

  /**
   * Finds a product by its ID.
   * 
   * @param id Product ID to search for
   * @return Product if found, null otherwise
   */
  // DESIGN PRINCIPLE: Stream API - modern Java functional programming
  public Product findById(int id) {
    return products.stream()
        .filter(p -> p.getId() == id)
        .findFirst()
        .orElse(null);
  }

  /**
   * Saves a new product with auto-generated ID.
   * 
   * @param name Product name
   * @param price Product price
   * @param category Product category
   * @param description Product description
   * @param stock Stock quantity
   * @return The newly created product
   */
  // DESIGN PRINCIPLE: Fail Fast - auto-generate ID to prevent user errors
  public Product save(String name, double price, Category category, 
                      String description, int stock) {
    int id = getNextId();
    Product newProduct = new Product(id, name, price, category, description, stock);
    products.add(newProduct);
    return newProduct;
  }

  /**
   * Updates an existing product.
   * 
   * @param product Product with updated information
   */
  public void update(Product product) {
    for (Product p : products) {
      if (p.getId() == product.getId()) {
        p.setName(product.getName());
        p.setPrice(product.getPrice());
        p.setCategory(product.getCategory());
        p.setDescription(product.getDescription());
        p.setStock(product.getStock());
        break;
      }
    }
  }

  /**
   * Deletes a product by ID.
   * 
   * @param id ID of product to delete
   */
  public void deleteById(int id) {
    products.removeIf(p -> p.getId() == id);
  }

  /**
   * Searches products by name (case-insensitive).
   * 
   * @param query Search term
   * @return List of matching products
   */
  // DESIGN PRINCIPLE: DRY (Don't Repeat Yourself) - reusable search method
  public List<Product> searchByName(String query) {
    if (query == null || query.trim().isEmpty()) {
      return findAll();
    }
    
    String lowerQuery = query.toLowerCase();
    
    return products.stream()
        .filter(p -> p.getName().toLowerCase().contains(lowerQuery))
        .collect(Collectors.toList());
  }

  /**
   * Filters products by category.
   * 
   * @param category Category to filter by
   * @return List of products in that category
   */
  public List<Product> findByCategory(Category category) {
    if (category == null) {
      return findAll();
    }
    
    return products.stream()
        .filter(p -> p.getCategory() == category)
        .collect(Collectors.toList());
  }

  /**
   * Searches and filters products by name and category.
   * 
   * @param query Search term (can be null)
   * @param category Category filter (can be null)
   * @return List of matching products
   */
  public List<Product> searchAndFilter(String query, Category category) {
    List<Product> results = new ArrayList<>(products);
    
    if (query != null && !query.trim().isEmpty()) {
      String lowerQuery = query.toLowerCase();
      results = results.stream()
          .filter(p -> p.getName().toLowerCase().contains(lowerQuery))
          .collect(Collectors.toList());
    }
    
    if (category != null) {
      results = results.stream()
          .filter(p -> p.getCategory() == category)
          .collect(Collectors.toList());
    }
    
    return results;
  }
  
  /**
   * Returns total number of products.
   * 
   * @return Product count
   */
  public int count() {
    return products.size();
  }
  
  /**
   * Checks if a product with given ID exists.
   * 
   * @param id Product ID to check
   * @return true if product exists, false otherwise
   */
  public boolean existsById(int id) {
    return findById(id) != null;
  }
}
