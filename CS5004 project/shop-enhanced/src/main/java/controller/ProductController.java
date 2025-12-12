package com.example.shop.controller;

import com.example.shop.model.Product;
import com.example.shop.model.Category;
import com.example.shop.service.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling product-related web requests.
 * Manages product CRUD operations, search, and filtering.
 * 
 *
 *
 */
// DESIGN PRINCIPLE: MVC Pattern - Controller handles HTTP requests and delegates to Service
@Controller
@RequestMapping("/products")
public class ProductController {

  // DESIGN PRINCIPLE: Dependency Injection - Spring provides the service
  private final ProductService service;

  /**
   * Constructor with dependency injection.
   * 
   * @param service ProductService for business logic
   */
  public ProductController(ProductService service) {
    this.service = service;
  }

  /**
   * Displays all products with optional search and filter.
   * 
   * @param search Optional search query
   * @param category Optional category filter
   * @param model Model for passing data to view
   * @return View name "products"
   */
  // DESIGN PRINCIPLE: Single Responsibility - only handles web requests, delegates logic to service
  @GetMapping
  public String listProducts(
      @RequestParam(required = false) String search,
      @RequestParam(required = false) Category category,
      Model model) {
    
    List<Product> products;
    
    if (search != null || category != null) {
      products = service.searchAndFilter(search, category);
    } else {
      products = service.getAllProducts();
    }
    
    model.addAttribute("products", products);
    model.addAttribute("categories", Category.values());
    model.addAttribute("currentSearch", search);
    model.addAttribute("currentCategory", category);
    
    return "products";
  }

  /**
   * Shows form to add new product.
   * 
   * @param model Model for passing data to view
   * @return View name "new-product"
   */
  @GetMapping("/new")
  public String newProductForm(Model model) {
    model.addAttribute("categories", Category.values());
    return "new-product";
  }

  /**
   * Shows form to edit existing product.
   * 
   * @param id Product ID to edit
   * @param model Model for passing data to view
   * @return View name "edit-product"
   */
  @GetMapping("/edit/{id}")
  public String editProduct(@PathVariable int id, Model model) {
    Product product = service.getProductById(id);
    model.addAttribute("product", product);
    model.addAttribute("categories", Category.values());
    return "edit-product";
  }

  /**
   * Handles new product form submission.
   * 
   * @param name Product name
   * @param price Product price
   * @param category Product category
   * @param description Product description
   * @param stock Stock quantity
   * @return Redirect to products list
   */
  @PostMapping("/create")
  public String createProduct(
      @RequestParam String name,
      @RequestParam double price,
      @RequestParam Category category,
      @RequestParam String description,
      @RequestParam int stock) {
    
    service.addProduct(name, price, category, description, stock);
    return "redirect:/products";
  }

  /**
   * Handles product update form submission.
   * 
   * @param id Product ID
   * @param name Updated name
   * @param price Updated price
   * @param category Updated category
   * @param description Updated description
   * @param stock Updated stock
   * @return Redirect to products list
   */
  @PostMapping("/update")
  public String updateProduct(
      @RequestParam int id,
      @RequestParam String name,
      @RequestParam double price,
      @RequestParam Category category,
      @RequestParam String description,
      @RequestParam int stock) {
    
    service.updateProduct(id, name, price, category, description, stock);
    return "redirect:/products";
  }

  /**
   * Deletes a product by ID.
   * 
   * @param id Product ID to delete
   * @return Redirect to products list
   */
  @GetMapping("/delete/{id}")
  public String deleteProduct(@PathVariable int id) {
    service.deleteProduct(id);
    return "redirect:/products";
  }
}
