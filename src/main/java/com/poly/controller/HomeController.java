package com.poly.controller;

import com.poly.service.CategoryService;
import com.poly.service.ProductService;
import com.poly.service.UserService;
import com.poly.utils.CookieUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    UserService service;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.getAllProducts());

        String username = CookieUtils.getCookieValue(request, "username");
        model.addAttribute("username", username);
        return "index";
    }
}