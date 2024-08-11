package com.tia102g4.vote.handler;

import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Type;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tia102g4.util.JedisUtil;
import com.tia102g4.rest.model.Restaurant;
import com.tia102g4.rest.to.RestaurantDTO;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

@WebServlet("/frontstage/memberFrontend/vote/option.do")
public class JedisOptionHandler extends HttpServlet{
	
	private static JedisPool pool = null;
	private static Jedis jedis = null;
	
	@Override
	public void init() throws ServletException{
		pool = JedisUtil.getJedisPool();
		jedis = pool.getResource();
		System.out.println(jedis.ping());
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
		case "option":
			forwardPath = preparing(req,res);
			break;
		default:
			forwardPath = "/frontstage/memberFrontend/vote/preparing_vote.jsp";
		}
		
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}
	
	//轉交三間餐廳當成投票選項到投票頁面
	private String preparing(HttpServletRequest req, HttpServletResponse res) {
//		前端資料
//		restList: [{"restaurant-id":"10","item_name":"大華咖啡廳","item_description":"提供香濃美味的咖啡。"}
//		,{"restaurant-id":"9","item_name":"小芳火鍋","item_description":"提供豐富多樣的火鍋選擇。"}
//		,{"restaurant-id":"7","item_name":"大志素食餐廳","item_description":"提供健康美味的素食餐點。"}]
		String restListJson = req.getParameter("restList");
		System.out.println(restListJson);
		Gson gson = new Gson();
		
		Type listType = new TypeToken<ArrayList<RestaurantDTO>>(){}.getType();
        List<RestaurantDTO> restaurantDTOs = gson.fromJson(restListJson, listType);
		
		List<Restaurant> restaurants = new ArrayList<>();
		for(RestaurantDTO dto : restaurantDTOs) {
			Restaurant restaurant = new Restaurant();
            restaurant.setRestId(Long.parseLong(dto.getRestaurantId()));
            restaurant.setRestName(dto.getItemName());
            restaurant.setDescription(dto.getItemDescription());
            restaurants.add(restaurant);
		}
		
		for(Restaurant restaurant : restaurants) {
			System.out.println("Restaurant ID: " + restaurant.getRestId());
            System.out.println("Restaurant Name: " + restaurant.getRestName());
            System.out.println("Description: " + restaurant.getDescription());
            System.out.println("--------------------");
		}
		req.setAttribute("restaurants", restaurants); //讓三個restaurant物件裝進restaurants裡面
		
		return "/frontstage/memberFrontend/vote/voting.jsp";
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		doPost(req,res);
	}
	
	@Override
	public void destroy() {
		jedis.close();
		JedisUtil.shutdownJedisPool();
	}
	
	

}
