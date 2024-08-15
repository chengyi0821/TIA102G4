package com.tia102g4.rest.controller;

import com.tia102g4.rest.model.RestaurantVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RestaurantController")
public class RestaurantController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<RestaurantVO> restaurants = getAllRestaurants();
//        request.setAttribute("restaurants", restaurants);
//        request.getRequestDispatcher("voting.jsp").forward(request, response);
    	doPost( request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("choose".equals(action)) {
            Long restId = Long.parseLong(request.getParameter("restId"));
            RestaurantVO chosenRestaurant = getRestaurantById(restId);
            
            HttpSession session = request.getSession();
            List<RestaurantVO> restList = (List<RestaurantVO>) session.getAttribute("restList");
            if (restList == null) {
                restList = new ArrayList<>();
            }
            restList.add(chosenRestaurant);
            session.setAttribute("restList", restList);
            
            response.sendRedirect("/frontstage/memberFrontend/vote/voting.jsp");
        }
    }

    private List<RestaurantVO> getAllRestaurants() {
        // 實作從Redis獲取所有餐廳的邏輯
    	// 已經在RestaurantVO裡面存入key為 restId:restType:restName:desctiption的選項值
    	//要想辦法打印出來  PrintWriter out = res.getWriter(); 使用這個方法?
        return new ArrayList<>();
    }

    private RestaurantVO getRestaurantById(Long restId) {
        // 實作從Redis獲取特定餐廳的邏輯
        return new RestaurantVO(restId, 0L, "","");
    }
}
