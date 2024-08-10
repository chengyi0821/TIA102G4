package com.tia102g4.vote.handler;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tia102g4.util.JedisUtil;
import com.tia102g4.rest.model.RestaurantVO;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

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
		Enumeration<String> restNames = req.getParameterNames();
		List<Long> selectedRestaurantIds = new ArrayList<>();

		while(restNames.hasMoreElements()) {
			String restName = restNames.nextElement();
			
			if(restName.matches("\\d+")) {
				Long restaurantId = Long.parseLong(restName);
				selectedRestaurantIds.add(restaurantId);
			}
		}
		//資料格式 key:value = 7:restaurant7 帶去投票寫出標籤 要去除action = "option"
		//只需要取得 餐廳ID即可使用查找的方式帶出餐廳基本資料
		//現在會有 restaurants:1 restaurants:2 restaurants:3   action:option (要去掉這個) 然後把前面三個的值裝在list
		Map<String,String[]> restMap = req.getParameterMap();

		String[] restaurantIds = restMap.get("restaurants");
		List<Long> restIds = new ArrayList<>();
		
		for(String id : restaurantIds) {
			selectedRestaurantIds.add(Long.parseLong(id));
		}
		
		List<RestaurantVO> selectedRestaurants = RestaurantVO.getRestaurantsByIds(restIds);
		
		req.getSession().setAttribute("selectedRestaurants", selectedRestaurants);
		
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
