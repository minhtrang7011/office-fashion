package com.poly.utils;

import com.poly.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtils {
    public static void setUserToSession(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("loggedInUser", user);
        session.setAttribute("roleId", user.getRoleId()); // Lưu roleId để kiểm tra

    }

    public static User getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (User) session.getAttribute("loggedInUser");
    }

    public static Integer getRoleIdFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Integer) session.getAttribute("roleId");
    }

    public static void removeUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    }
}
