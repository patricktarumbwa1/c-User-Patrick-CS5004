package com.example.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

/**
 * Controller for handling home page requests.
 * 
 *
 *
 */
// DESIGN PRINCIPLE: MVC Pattern - Controller handles web requests
@Controller
public class HomeController {

  /**
   * Displays the home page.
   * 
   * @param model Model for passing data to view
   * @return View name "home"
   */
  @GetMapping("/")
  public String homePage(Model model) {
    model.addAttribute("pageTitle", "Welcome to ShopHub!");
    return "home";
  }
}
