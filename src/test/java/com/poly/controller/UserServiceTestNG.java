package com.poly.controller;

import com.poly.model.User;

import com.poly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringBootTest
public class UserServiceTestNG {
    @Autowired
    private UserService userService;

    @Test
    public void testLoginSuccess() {
        var user = userService.login("minhtrangn", "password_2");
        Assert.assertNotNull(user, "Đăng nhập thành công thì không được null");
        Assert.assertEquals(user.getUsername(), "trangntm");
    }

    @Test
    public void testLoginWrongPassword() {
        User user = userService.login("trangntm", "123");
        Assert.assertNull(user);
    }

    @Test
    public void testLoginUserNotFound() {
        User user = userService.login("tamle", "123");
        Assert.assertNull(user);
    }
}
