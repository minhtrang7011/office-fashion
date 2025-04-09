package com.poly.controller;

import com.poly.model.User;
import com.poly.repository.UserRepository;
import com.poly.service.AuthService;
import com.poly.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/auth/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpServletResponse response,
                        Model model) {
        User user = userService.login(username, password);
        if (user != null) {
            // Tạo cookie
            Cookie cookie = new Cookie("username", user.getUsername());
            cookie.setMaxAge(60 * 60); // 1 tiếng
            cookie.setPath("/"); // Áp dụng toàn site
            response.addCookie(cookie);

            return "redirect:/"; // Chuyển về trang chủ
        } else {
            model.addAttribute("error", "Sai tên đăng nhập hoặc mật khẩu");
            return "login";
        }
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            Model model
    ) {
        // 1. Kiểm tra trùng username
        if (userService.getUserByUsername(username).isPresent()) {
            model.addAttribute("error", "Tên đăng nhập đã tồn tại!");
            return "register";
        }

        // 2. Kiểm tra xác nhận mật khẩu
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Mật khẩu không khớp!");
            return "register";
        }

        // 3. Lưu user mới
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // thực tế nên mã hóa mật khẩu
        user.setRoleId(2); // mặc định role người dùng thường

        userService.saveUser(user);

        return "redirect:/login"; // chuyển sang trang login
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("username", "");
        cookie.setMaxAge(0); // Hết hạn ngay
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/";
    }
}
