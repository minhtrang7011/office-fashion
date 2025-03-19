package com.poly.controller;

import com.poly.model.User;
import com.poly.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            model.addAttribute("error", "Vui lòng đăng nhập để xem giỏ hàng.");
            return "cart";
        }

        User user = new User();
        user.setId(userId);

        model.addAttribute("cartItems", cartService.getCartByUser(user));
        model.addAttribute("totalPrice", cartService.getTotalPrice(user));

        return "cart";
    }
//
//    @GetMapping("/cart/add/{productId}")
//    public String addToCart(@PathVariable Long productId, @RequestParam("userId") Long userId) {
//        User user = new User(); // Cần lấy user từ session hoặc database
//        user.setId(userId);
//        cartService.addToCart(user, productId, 1);
//        return "redirect:/cart?userId=" + userId;
//    }
//
//    @GetMapping("/cart/remove/{cartId}")
//    public String removeFromCart(@PathVariable Long cartId, @RequestParam("userId") Long userId) {
//        cartService.removeFromCart(cartId);
//        return "redirect:/cart?userId=" + userId;
//    }
//
//    @GetMapping("/cart/clear")
//    public String clearCart(@RequestParam("userId") Long userId) {
//        User user = new User();
//        user.setId(userId);
//        cartService.clearCart(user);
//        return "redirect:/cart?userId=" + userId;
//    }
}
