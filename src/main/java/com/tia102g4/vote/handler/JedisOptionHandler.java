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
import com.tia102g4.event.model.*;

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
		case "choose":
			forwardPath = voting(req,res);
			break;
		default:
			forwardPath = "/frontstage/memberFrontend/vote/preparing_vote.jsp";
		}
		
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}
	
	//轉交三間餐廳當成投票選項到投票頁面，取得eventId當作redis的key option是選取的三家餐廳ID
	private String preparing(HttpServletRequest req, HttpServletResponse res) {
		String restListJson = req.getParameter("restList");
		System.out.println(restListJson);
		Gson gson = new Gson();
		String redisKey = null;
		List<String> options = new ArrayList<>();
		
		List<Event> eventList = (List<Event>) req.getSession().getAttribute("eventList");
		for(Event event : eventList) {
			Long eventId = event.getEventId();
			redisKey = "event:"+eventId;
		}
		Type listType = new TypeToken<ArrayList<RestaurantDTO>>(){}.getType();
        List<RestaurantDTO> restaurantDTOs = gson.fromJson(restListJson, listType);
		
		List<Restaurant> restaurants = new ArrayList<>();
		for(RestaurantDTO dto : restaurantDTOs) {
			Restaurant restaurant = new Restaurant();
			
            restaurant.setRestId(Long.parseLong(dto.getRestaurantId())); 
            restaurant.setRestName(dto.getItemName());
            restaurant.setDescription(dto.getItemDescription());
            restaurants.add(restaurant);
            
            String option = "option:"+Integer.parseInt(dto.getRestaurantId());
            options.add(option); //options裡面有三個Filed:restaurantId
            jedis.hset(redisKey, option,"0");//製作出三個選項代表Key:eventId , Filed:restaurantId, Value:票數
            jedis.expire(redisKey, 86400); 
		}
		
		req.getSession().setAttribute("eventList", eventList);
		req.getSession().setAttribute("restaurants", restaurants); 
		req.getSession().setAttribute("redisKey",redisKey);
		return "/frontstage/memberFrontend/vote/voting.jsp";
	}
	
	private String voting(HttpServletRequest req, HttpServletResponse res) {
//		Long restId = Long.parseLong(req.getParameter("restId"));//取得使用者點選的餐廳，使用input type='radio'
		String key = String.valueOf(req.getSession().getAttribute("redisKey"));
		
		String selectedOption = req.getParameter("selectedOption");
		jedis.hincrBy(key, selectedOption, 1); 
		Map<String, String> voteCount = jedis.hgetAll(key);
		req.getSession().setAttribute("count", voteCount);
		return "/frontstage/memberFrontend/room/testcheckroom.jsp"; //導向之後讓大家等待投票結束
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
