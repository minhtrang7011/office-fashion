package com.poly.controller.admin;

import com.poly.model.Product;
import com.poly.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/product")
public class ProductAController {
    private final ProductRepository productRepository;

    public ProductAController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "admin/product";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "admin/product-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/admin/product/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("product", productRepository.findById(id).orElse(null));
        return "admin/product-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/admin/product/index";
    }
}
