package com.poly.selenium;

import com.poly.controller.AuthController;
import com.poly.model.User;
import com.poly.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegisterTest {
    @InjectMocks
    private AuthController authController;

    @Mock
    private UserService userService;

    private Model model;

    @BeforeEach
    void setUp() {
        model = new ConcurrentModel();
    }

    @Test
    void testRegisterSuccess() {
        // Given
        String username = "newuser";
        String password = "123";
        String confirmPassword = "123";

        when(userService.getUserByUsername(username)).thenReturn(Optional.empty());

        // When
        String result = authController.register(username, password, confirmPassword, model);

        // Then
        assertEquals("redirect:/login", result);
        verify(userService).saveUser(any(User.class));
    }


    @Test
    void testRegisterFail_UsernameExists() {
        // Given
        String username = "trangntm";
        String password = "123";
        String confirmPassword = "123";

        when(userService.getUserByUsername(username)).thenReturn(Optional.of(new User()));

        // When
        String result = authController.register(username, password, confirmPassword, model);

        // Then
        assertEquals("register", result);
        assertEquals("Tên đăng nhập đã tồn tại!", model.getAttribute("error"));
    }

    @Test
    void testRegisterFail_PasswordMismatch() {
        // Given
        String username = "newuser";
        String password = "123";
        String confirmPassword = "456";

        when(userService.getUserByUsername(username)).thenReturn(Optional.empty());

        // When
        String result = authController.register(username, password, confirmPassword, model);

        // Then
        assertEquals("register", result);
        assertEquals("Mật khẩu không khớp!", model.getAttribute("error"));
    }
}