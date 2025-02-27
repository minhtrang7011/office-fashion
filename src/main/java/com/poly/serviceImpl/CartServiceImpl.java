package com.poly.serviceImpl;

import com.poly.entity.Cart;
import com.poly.entity.Product;
import com.poly.entity.User;
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
//
//    @Override
//    public void addToCart(User user, Long productId, int quantity) {
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));
//        List<Cart> userCart = cartRepository.findByUser(user);
//
//        for (Cart cartItem : userCart) {
//            if (cartItem.getProduct().getId().equals(productId)) {
//                cartItem.setQuantity(cartItem.getQuantity() + quantity);
//                cartRepository.save(cartItem);
//                return;
//            }
//        }
//
//        Cart newCartItem = new Cart();
//        newCartItem.setUser(user);
//        newCartItem.setProduct(product);
//        newCartItem.setQuantity(quantity);
//        cartRepository.save(newCartItem);
//    }
//
//    @Override
//    public void updateQuantity(Long cartId, int quantity) {
//        Cart cartItem = cartRepository.findById(cartId)
//                .orElseThrow(() -> new RuntimeException("Sản phẩm trong giỏ hàng không tồn tại"));
//        cartItem.setQuantity(quantity);
//        cartRepository.save(cartItem);
//    }
//
//    @Override
//    public void removeFromCart(Long cartId) {
//        cartRepository.deleteById(cartId);
//    }
//
    @Override
    public double getTotalPrice(User user) {
        List<Cart> userCart = cartRepository.findByUser(user);
        return userCart.stream().mapToDouble(cart -> cart.getProduct().getPrice() * cart.getQuantity()).sum();
    }
//
//    @Override
//    public void clearCart(User user) {
//        List<Cart> userCart = cartRepository.findByUser(user);
//        cartRepository.deleteAll(userCart);
//    }
}
