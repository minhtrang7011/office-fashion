package com.poly.controller.admin;

import com.poly.model.Category;
import com.poly.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/category")
public class CategoryAController {
    private final CategoryRepository categoryRepository;

    public CategoryAController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/category";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Category category) {
        categoryRepository.save(category);
        return "redirect:/admin/category/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryRepository.findById(id).orElse(null));
        return "admin/category-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return "redirect:/admin/category/index";
    }
}
