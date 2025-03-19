package com.poly.service;

import com.poly.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(Long categoryId);
    Product getProductById(Long id);
}
