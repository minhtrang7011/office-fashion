package com.poly.service;

import com.poly.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category getCategoryById(Long id);
}
