package com.example.shop.model;

/**
 * Product model representing an item in the shop.
 * Contains product information like name, price, category, description, and stock.
 * 
 *
 *
 */
// DESIGN PRINCIPLE: Encapsulation - private fields with public getters/setters
public class Product {

  // DESIGN PRINCIPLE: Meaningful Names - clear, descriptive field names
  private int id;
  private String name;
  private double price;
  private Category category;
  private String description;
  private int stock;

  /**
   * Creates a new Product with all required information.
   * 
   * @param id Unique product identifier
   * @param name Product name
   * @param price Product price in dollars
   * @param category Product category (CLOTHES, ELECTRONICS, etc.)
   * @param description Detailed product description
   * @param stock Available quantity
   */
  public Product(int id, String name, double price, Category category, 
                 String description, int stock) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.category = category;
    this.description = description;
    this.stock = stock;
  }

  // ===== GETTERS =====

  /**
   * Gets the product ID.
   * 
   * @return Product ID
   */
  public int getId() { 
    return id; 
  }
  
  /**
   * Gets the product name.
   * 
   * @return Product name
   */
  public String getName() { 
    return name; 
  }
  
  /**
   * Gets the product price.
   * 
   * @return Product price in dollars
   */
  public double getPrice() { 
    return price; 
  }
  
  /**
   * Gets the product category.
   * 
   * @return Product category
   */
  public Category getCategory() { 
    return category; 
  }
  
  /**
   * Gets the product description.
   * 
   * @return Product description
   */
  public String getDescription() { 
    return description; 
  }
  
  /**
   * Gets the current stock quantity.
   * 
   * @return Number of items in stock
   */
  public int getStock() { 
    return stock; 
  }

  // ===== SETTERS =====
  // DESIGN PRINCIPLE: Immutability - no setId() method, ID cannot change after creation

  /**
   * Sets the product name.
   * 
   * @param name New product name
   */
  public void setName(String name) { 
    this.name = name; 
  }
  
  /**
   * Sets the product price.
   * 
   * @param price New price in dollars
   */
  public void setPrice(double price) { 
    this.price = price; 
  }
  
  /**
   * Sets the product category.
   * 
   * @param category New category
   */
  public void setCategory(Category category) { 
    this.category = category; 
  }
  
  /**
   * Sets the product description.
   * 
   * @param description New description
   */
  public void setDescription(String description) { 
    this.description = description; 
  }
  
  /**
   * Sets the stock quantity.
   * 
   * @param stock New stock quantity
   */
  public void setStock(int stock) { 
    this.stock = stock; 
  }
  
  // ===== HELPER METHODS =====

  /**
   * Checks if product is in stock.
   * 
   * @return true if stock is greater than 0, false otherwise
   */
  public boolean isInStock() {
    return stock > 0;
  }
  
  /**
   * Checks if product stock is low (less than 5 items).
   * 
   * @return true if stock is between 1 and 4, false otherwise
   */
  public boolean isLowStock() {
    return stock > 0 && stock < 5;
  }
}
