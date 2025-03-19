package com.poly.controller;

import com.poly.model.User;
import com.poly.repository.UserRepository;
import com.poly.service.AuthService;
import com.poly.utils.SessionUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/auth/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        User user = authService.authenticate(username, password);
        if (user != null) {
            SessionUtils.setUserToSession(request, user);

            // Nếu là Admin, chuyển hướng về trang Admin
            if (user.getRoleId().equals(1)) {
                return "redirect:/admin/dashboard";
            }
            return "redirect:/"; // Nếu là User, về trang chủ
        }
        return "redirect:/auth/login?error=true";
    }

//    @GetMapping("/register")
//    public String showRegisterPage(Model model) {
//        model.addAttribute("user", new User());
//        return "register";
//    }
//
//    @PostMapping("/register")
//    public String register(
//            @RequestParam String username,
//            @RequestParam String password,
//            @RequestParam String confirmPassword,
//            @RequestParam String email,
//            @RequestParam String phone,
//            @RequestParam String address,
//            Model model) {
//
//        // Kiểm tra mật khẩu xác nhận
//        if (!password.equals(confirmPassword)) {
//            model.addAttribute("msg", "Mật khẩu xác nhận không khớp!");
//            return "register";
//        }
//
//        // Kiểm tra username đã tồn tại chưa
//        if (userRepository.findByUsername(username).isPresent()) {
//            model.addAttribute("msg", "Username is already taken");
//            return "register";
//        }
//
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setEmail(email);
//        user.setPhone(phone);
//        user.setAddress(address);
//        user.setRoleId(2);  // Mặc định role là USER (ID = 2)
//
//        // Lưu vào database
//        userRepository.save(user);
//
//        model.addAttribute("msg", "User registered successfully");
//        return "login";
//    }

    @GetMapping("/auth/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate(); // Xóa session khi logout
        return "redirect:/auth/login";
    }
}
