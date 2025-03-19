package com.poly.controller;

import com.poly.model.Category;
import com.poly.model.Product;
import com.poly.service.CategoryService;
import com.poly.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/{id}")
    public String categoryPage(@PathVariable("id") Long categoryId, Model model) {
        List<Product> products = productService.getProductsByCategory(categoryId);
        Category category = categoryService.getCategoryById(categoryId);

        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "fragments/category";
    }
}