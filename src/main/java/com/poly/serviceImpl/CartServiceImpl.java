package com.poly.serviceImpl;

import com.poly.model.Cart;
import com.poly.model.User;
import com.poly.repository.CartRepository;
import com.poly.repository.ProductRepository;
import com.poly.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Cart> getCartByUser(User user) {
        return cartRepository.findByUser(user);
    }

//    @Override
//    public double getTotalPrice(User user) {
//       List<Cart> userCart = cartRepository.findByUser(user);
//        return userCart.stream().mapToDouble(cart -> cart.getProduct().getPrice() * cart.getQuantity()).sum();
//    }
}
