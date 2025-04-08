package com.poly.service;

import com.poly.model.Cart;
import com.poly.model.User;

import java.util.List;

public interface CartService {
    List<Cart> getCartByUser(User user);
//
//    void addToCart(User user, Long productId, int quantity);
//
//    void updateQuantity(Long cartId, int quantity);
//
//    void removeFromCart(Long cartId);
//
//    double getTotalPrice(User user);
//
//    void clearCart(User user);
}

