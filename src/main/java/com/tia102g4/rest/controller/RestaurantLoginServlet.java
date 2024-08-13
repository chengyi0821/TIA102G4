package com.tia102g4.rest.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tia102g4.rest.model.Restaurant;
import com.tia102g4.rest.service.RestaurantService;
import com.tia102g4.rest.service.RestaurantServiceImpl;

@WebServlet("/restLogin")
public class RestaurantLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RestaurantService restaurantService = new RestaurantServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Restaurant restaurant = restaurantService.login(email, password);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (restaurant != null) {
                HttpSession session = request.getSession();
                session.setAttribute("restaurant", restaurant);

                response.getWriter().write("{\"success\": true}");
            } else {
                response.getWriter().write("{\"success\": false, \"message\": \"Invalid email or password.\"}");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("{\"success\": false, \"message\": \"An error occurred. Please try again.\"}");
        }
    }
}
