package com.tia102g4.rest.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tia102g4.rest.model.Restaurant;
import com.tia102g4.rest.service.RestaurantService;
import com.tia102g4.rest.service.RestaurantServiceImpl;
import java.io.InputStream;

@WebServlet("/restaurantServlet")
public class RestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RestaurantService restaurantService = new RestaurantServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            registerRestaurant(request, response);
        } else if ("login".equals(action)) {
            loginRestaurant(request, response);
        } else if ("resetPassword".equals(action)) {
            resetPassword(request, response);
        } else if ("forgotPassword".equals(action)) {
            forgotPassword(request, response);
        } else if ("updateRestaurant".equals(action)) {
            updateRestaurant(request, response);
        }
    }

    private void registerRestaurant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestName(request.getParameter("rest_name"));
        restaurant.setDescription(request.getParameter("description"));
        restaurant.setLocation(request.getParameter("location"));
        restaurant.setPhone(request.getParameter("phone"));
        restaurant.setEmail(request.getParameter("email"));
        restaurant.setPassword(request.getParameter("password"));
        restaurant.setAccount(request.getParameter("account"));

        // 將 sticker 部分處理為 byte[]
        InputStream stickerInputStream = request.getPart("sticker").getInputStream();
        byte[] stickerBytes = stickerInputStream.readAllBytes();
        restaurant.setSticker(stickerBytes);

        restaurant.setOpenDay(request.getParameter("open_day"));
        restaurant.setOpenTime1(Time.valueOf(request.getParameter("open_time1")));
        restaurant.setCloseTime1(Time.valueOf(request.getParameter("close_time1")));
        restaurant.setOpenTime2(Time.valueOf(request.getParameter("open_time2")));
        restaurant.setCloseTime2(Time.valueOf(request.getParameter("close_time2")));
        restaurant.setAccStatus(true); // 系統自動生成

        try {
            restaurantService.register(restaurant);
            response.sendRedirect("restLogin.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    // 其他方法不需要更改，與 sticker 無關
    private void loginRestaurant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        try {
            Restaurant restaurant = restaurantService.login(account, password);
            if (restaurant != null) {
                HttpSession session = request.getSession();
                session.setAttribute("restaurant", restaurant);
                response.sendRedirect("restMain.jsp");
            } else {
                response.sendRedirect("restLogin.jsp?error=Invalid account or password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resetToken = request.getParameter("reset_token");
        String newPassword = request.getParameter("new_password");

        try {
            Restaurant restaurant = restaurantService.findByResetToken(resetToken);
            if (restaurant != null) {
                restaurantService.updatePassword(restaurant.getRestId(), newPassword);
                response.sendRedirect("restLogin.jsp?success=Password reset successfully");
            } else {
                response.sendRedirect("restResetPassword.jsp?error=Invalid reset token");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void forgotPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        try {
            Restaurant restaurant = restaurantService.findByEmail(email);
            if (restaurant != null) {
                String resetToken = generateResetToken();
                restaurantService.resetPasswordToken(restaurant.getRestId(), resetToken);
                // 發送重設密碼郵件的邏輯
                response.sendRedirect("restForgotPassword.jsp?success=Password reset email sent");
            } else {
                response.sendRedirect("restForgotPassword.jsp?error=Email not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void updateRestaurant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");

        if (restaurant != null) {
            restaurant.setRestName(request.getParameter("rest_name"));
            restaurant.setDescription(request.getParameter("description"));
            restaurant.setLocation(request.getParameter("location"));
            restaurant.setPhone(request.getParameter("phone"));
            restaurant.setEmail(request.getParameter("email"));
            restaurant.setOpenDay(request.getParameter("open_day"));
            restaurant.setOpenTime1(Time.valueOf(request.getParameter("open_time1")));
            restaurant.setCloseTime1(Time.valueOf(request.getParameter("close_time1")));
            restaurant.setOpenTime2(Time.valueOf(request.getParameter("open_time2")));
            restaurant.setCloseTime2(Time.valueOf(request.getParameter("close_time2")));

            try {
                restaurantService.updateRestaurant(restaurant);
                request.getSession().setAttribute("restaurant", restaurant);
                response.sendRedirect("restMain.jsp?success=Information updated successfully");
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("restLogin.jsp?error=Please login first");
        }
    }

    private String generateResetToken() {
        // 生成隨機重設密碼的令牌
        return java.util.UUID.randomUUID().toString();
    }
}
