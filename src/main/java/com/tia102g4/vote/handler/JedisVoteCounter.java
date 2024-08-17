package com.tia102g4.vote.handler;

import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tia102g4.util.JedisUtil;
import com.tia102g4.event.model.*;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@WebServlet("/vote/count.do")
public class JedisVoteCounter extends HttpServlet{

	private static JedisPool pool = null;
	private static Jedis jedis = null;
	
	@Override
	public void init() throws ServletException{
		pool = JedisUtil.getJedisPool();
		jedis = pool.getResource();
	}
	
	//讓Ajax定時過來執行這個方法
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		String redisKey = String.valueOf(req.getSession().getAttribute("redisKey"));
		String[] choices = (String[]) req.getSession().getAttribute("choices");
		//執行jedis.hgetAll(redisKey)拿到票數
		Map<String, String> voteCounts = null;
		
		try {
			voteCounts = jedis.hgetAll(redisKey);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(voteCounts);
//		{option:3: '1', option:56: '0', option:55: '0'}  JSON長這個樣子 想辦法把值去替換掉票數裡面的數字
		res.setContentType("applicaion/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(json);
		
		int maxSeat = 0;
		List<Event> eventList = (List<Event>) req.getSession().getAttribute("eventList");
		if(eventList != null) {
		maxSeat = eventList.get(0).getMaxseat(); //從eventList取得maxSeat，只要裝一次
		req.getSession().setAttribute("maxSeat", maxSeat);
		}
		
		int totalVotes = voteCounts.values().stream().mapToInt(Integer::parseInt).sum(); //確定抓到totalVote
		System.out.println("totalVotes:"+totalVotes);
		req.getSession().setAttribute("totalVotes", totalVotes);
		
		//winningRestId要抓出來因為每次訪問都會重新賦值，所以要在取得的時候裝進Session
		String winningRestId = "";
		
		//當總投票數達到最大出席人數，取得當選餐廳的ID
		if(totalVotes == maxSeat) {
			Map<String, Integer> restVoteCounts = new HashMap<>();

            for (String choice : choices) {
                String restId = choice.split(":")[1];
                int votes = Integer.parseInt(voteCounts.getOrDefault(choice, "0"));
                restVoteCounts.put(restId, votes);
                System.out.println("restVoteCounts : "+restVoteCounts);
            }
			winningRestId = restVoteCounts.entrySet().stream()
	                .max(Map.Entry.comparingByValue())
	                .map(Map.Entry::getKey)
	                .orElse("");
			System.out.println("winningRestId:"+winningRestId);
			req.getSession().setAttribute("winningRestId", winningRestId);
		}
		
		
	}
	
	@Override
	public void destroy() {
		jedis.close();
		JedisUtil.shutdownJedisPool();
	}
}
