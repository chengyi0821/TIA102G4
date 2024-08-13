package com.tia102g4.rest.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tia102g4.rest.model.Restaurant;
import com.tia102g4.rest.service.RestaurantService;
import com.tia102g4.rest.service.RestaurantServiceImpl;

@WebServlet("/restResetPassword")
public class RestResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RestaurantService restaurantService = new RestaurantServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resetToken = request.getParameter("token");
        String newPassword = request.getParameter("newPassword");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            Restaurant restaurant = restaurantService.findByResetToken(resetToken);
            if (restaurant != null) {
                restaurantService.updatePassword(restaurant.getRestId(), newPassword);
                response.getWriter().write("{\"success\": true, \"message\": \"Password reset successfully\"}");
            } else {
                response.getWriter().write("{\"success\": false, \"message\": \"Invalid reset token\"}");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"success\": false, \"message\": \"Server error occurred\"}");
        }
    }
}


//package com.tia102g4.rest.controller;
//
//import java.io.IOException;
//import java.sql.SQLException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.tia102g4.rest.model.Restaurant;
//import com.tia102g4.rest.service.RestaurantService;
//import com.tia102g4.rest.service.RestaurantServiceImpl;
//
//@WebServlet("/restResetPassword")
//public class RestResetPasswordServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    
//    private RestaurantService restaurantService = new RestaurantServiceImpl();
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String resetToken = request.getParameter("reset_token");
//        String newPassword = request.getParameter("new_password");
//
//        if (resetToken == null || resetToken.isEmpty() || newPassword == null || newPassword.isEmpty()) {
//            response.sendRedirect("restResetPassword.jsp?error=Please provide valid input");
//            return;
//        }
//
//        try {
//            Restaurant restaurant = restaurantService.findByResetToken(resetToken);
//            if (restaurant != null) {
//                String hashedPassword = hashPassword(newPassword); // 假設有一個hashPassword方法
//                restaurantService.updatePassword(restaurant.getRestId(), hashedPassword);
//                response.sendRedirect("restLogin.jsp?success=Password reset successfully");
//            } else {
//                response.sendRedirect("restResetPassword.jsp?error=Invalid reset token");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace(); // 或者使用更好的日誌處理方法
//            response.sendRedirect("error.jsp");
//        }
//    }
//
//    private String hashPassword(String password) {
//        // 實現密碼哈希的邏輯
//        return password; // 這裡應該替換為實際的哈希處理
//    }
//}


//package com.tia102g4.rest.controller;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.tia102g4.rest.model.Restaurant;
//import com.tia102g4.rest.service.RestaurantService;
//import com.tia102g4.rest.service.RestaurantServiceImpl;
//
//@WebServlet("/restResetPassword")
//public class RestResetPasswordServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	
//	private RestaurantService restaurantService = new RestaurantServiceImpl();
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String resetToken = request.getParameter("reset_token");
//        String newPassword = request.getParameter("new_password");
//
//        try {
//            Restaurant restaurant = restaurantService.findByResetToken(resetToken);
//            if (restaurant != null) {
//                restaurantService.updatePassword(restaurant.getRestId(), newPassword);
//                response.sendRedirect("restLogin.jsp?success=Password reset successfully");
//            } else {
//                response.sendRedirect("restResetPassword.jsp?error=Invalid reset token");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            response.sendRedirect("error.jsp");
//        }
//    }
//}
