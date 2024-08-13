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
import com.tia102g4.rest.util.EmailUtil;

@WebServlet("/restForgotPassword")
public class RestForgotPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RestaurantService restaurantService = new RestaurantServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            Restaurant restaurant = restaurantService.findByEmail(email);
            if (restaurant != null) {
                String resetToken = generateResetToken();
                restaurantService.resetPasswordToken(restaurant.getRestId(), resetToken);
                
                // 发送重设密码邮件
                String subject = "Chugether 密碼重設";
                String resetLink = "http://localhost:8081/C080606/restResetPassword.jsp?token=" + resetToken; // 指向实际的重置密码页面
//                String resetLink = "http://chugether.myvnc.com:8081/C080606/restResetPassword.jsp?token=" + resetToken;
                String content = "<p>請點擊以下鏈接來重設您的密碼：</p>" +
                                 "<a href=\"" + resetLink + "\">重設密碼</a>";
                
                EmailUtil.sendEmail(email, subject, content);
                
                response.getWriter().write("{\"success\": true, \"message\": \"Password reset email sent\"}");
            } else {
                response.getWriter().write("{\"success\": false, \"message\": \"Email not found\"}");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"success\": false, \"message\": \"Server error occurred\"}");
        }
    }

    private String generateResetToken() {
        // 生成随机重设密码的令牌
        return java.util.UUID.randomUUID().toString();
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
//import com.tia102g4.rest.util.EmailUtil;
//
//@WebServlet("/restForgotPassword")
//public class RestForgotPasswordServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    private RestaurantService restaurantService = new RestaurantServiceImpl();
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String email = request.getParameter("email");
//
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//
//        try {
//            Restaurant restaurant = restaurantService.findByEmail(email);
//            if (restaurant != null) {
//                String resetToken = generateResetToken();
//                restaurantService.resetPasswordToken(restaurant.getRestId(), resetToken);
//                
//                // 发送重设密码邮件
//                String subject = "Chugether 密碼重設";
//                String resetLink = "http://yourwebsite.com/resetPassword?token=" + resetToken; // 替换为实际的重置密码链接
//                String content = "<p>請點擊以下鏈接來重設您的密碼：</p>" +
//                                 "<a href=\"" + resetLink + "\">重設密碼</a>";
//                
//                EmailUtil.sendEmail(email, subject, content);
//                
//                response.getWriter().write("{\"success\": true, \"message\": \"Password reset email sent\"}");
//            } else {
//                response.getWriter().write("{\"success\": false, \"message\": \"Email not found\"}");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            response.getWriter().write("{\"success\": false, \"message\": \"Server error occurred\"}");
//        }
//    }
//
//    private String generateResetToken() {
//        // 生成随机重设密码的令牌
//        return java.util.UUID.randomUUID().toString();
//    }
//}



//package com.tia102g4.rest.controller;
//
//	import java.io.IOException;
//	import java.sql.SQLException;
//	import javax.servlet.ServletException;
//	import javax.servlet.annotation.WebServlet;
//	import javax.servlet.http.HttpServlet;
//	import javax.servlet.http.HttpServletRequest;
//	import javax.servlet.http.HttpServletResponse;
//
//	import com.tia102g4.rest.model.Restaurant;
//	import com.tia102g4.rest.service.RestaurantService;
//	import com.tia102g4.rest.service.RestaurantServiceImpl;
//
//	@WebServlet("/restForgotPassword")
//	public class RestForgotPasswordSevlet extends HttpServlet {
//		private static final long serialVersionUID = 1L;
//		
//		private RestaurantService restaurantService = new RestaurantServiceImpl();
//
//	    @Override
//	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	        String email = request.getParameter("email");
//
//	        try {
//	            Restaurant restaurant = restaurantService.findByEmail(email);
//	            if (restaurant != null) {
//	                String resetToken = generateResetToken();
//	                restaurantService.resetPasswordToken(restaurant.getRestId(), resetToken);
//	                // 發送重設密碼郵件的邏輯
//	                response.sendRedirect("restForgotPassword.jsp?success=Password reset email sent");
//	            } else {
//	                response.sendRedirect("restForgotPassword.jsp?error=Email not found");
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	            response.sendRedirect("error.jsp");
//	        }
//	    }
//
//	    private String generateResetToken() {
//	        // 生成隨機重設密碼的令牌
//	        return java.util.UUID.randomUUID().toString();
//	    }
//	}
//
