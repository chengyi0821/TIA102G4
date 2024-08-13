package com.tia102g4.rest.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

import com.tia102g4.rest.model.Restaurant;
import com.tia102g4.rest.service.RestaurantService;
import com.tia102g4.rest.service.RestaurantServiceImpl;

@WebServlet("/restRegister")
public class RestRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RestaurantService restaurantService = new RestaurantServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestName(request.getParameter("rest_name"));
        restaurant.setDescription(request.getParameter("description"));
        restaurant.setLocation(request.getParameter("location"));
        restaurant.setPhone(request.getParameter("phone"));
        restaurant.setEmail(request.getParameter("email"));
        restaurant.setPassword(request.getParameter("password"));
        restaurant.setAccount(request.getParameter("account"));

        // 處理 sticker 部分為 byte[]
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
}
