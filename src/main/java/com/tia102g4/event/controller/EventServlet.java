package com.tia102g4.event.controller;

import java.io.IOException;
import java.util.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tia102g4.event.model.Event;
import com.tia102g4.member.model.Member;
import com.tia102g4.rest.model.Restaurant;
import com.tia102g4.util.CodeMaker;
import com.tia102g4.util.MakeFriends;
import com.tia102g4.event.service.EventService;
import com.tia102g4.event.service.EventServiceImpl;

@WebServlet("/event/event.do")
public class EventServlet extends HttpServlet{

	private EventService evtsvc;
	
	@Override
	public void init() throws ServletException{
		evtsvc = new EventServiceImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
		case "add":
			forwardPath = addEvent(req,res);
			break;
		case "getInfo":
			forwardPath = getEventInfo(req,res);
			break;
		case "getAll":
			forwardPath = getAllRestaurant(req,res);
			break;
		case "compositeQuery":
			forwardPath = getCompositeQuery(req,res);
			break;
		case "noshow":
			forwardPath = "/frontstage/memberFrontend/myorder/member_orderStatus1.jsp";
			break;
		case "getIn":
			forwardPath = "/frontstage/memberFrontend/myorder/member_orderStatus1.jsp";
			break;
		default:
			forwardPath = "/frontstage/memberFrontend/event/gate.jsp";
		}
		
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}

	private String addEvent(HttpServletRequest req, HttpServletResponse res) {
		String time = req.getParameter("time");
		String date = req.getParameter("date");
		String timeSteap =time+":00";
		String code = CodeMaker.invitationCode();
		Time mealtime = Time.valueOf(timeSteap);
		Member member = (Member) req.getSession().getAttribute("loggedInMember");
		
		Event entity = new Event();
		entity.setName(String.valueOf(req.getParameter("name").trim()));
		entity.setInfo(String.valueOf(req.getParameter("info").trim()));
		entity.setDate(Date.valueOf(req.getParameter("date")));
		entity.setMemberId(member.getMemberId());//取得會員ID
		entity.setTime(mealtime);
		entity.setMaxseat(Integer.valueOf(req.getParameter("maxseat")));
		entity.setCode(code);
		evtsvc.addEvent(entity);
		List<Event> eventList = new ArrayList<Event>();
		eventList.add(entity);
		MakeFriends friends = new MakeFriends();
		List<Member> memberList = friends.ImaginaryFriend(3);
		req.getSession().setAttribute("eventList", eventList);
		req.getSession().setAttribute("buildingEvnet", entity);
		req.getSession().setAttribute("memberList", memberList);
		return "/frontstage/memberFrontend/event/fellow.jsp";
	}
	
	private String getEventInfo(HttpServletRequest req, HttpServletResponse res) {
		String code = req.getParameter("code");
		if (!evtsvc.validateCode(code)) {
			req.setAttribute("errMsg", "查無此活動!請聯絡主揪者拿到正確邀請碼!");
		    return "/frontstage/memberFrontend/event/gate.jsp"; // 或者返回一個錯誤訊息頁面
		}else {
		List<Event> eventList = evtsvc.getInfo(code);
		MakeFriends friends = new MakeFriends();
		List<Member> memberList = friends.ImaginaryFriend(3);
		req.getSession().setAttribute("eventList", eventList);		
		req.getSession().setAttribute("memberList", memberList);
		return "/frontstage/memberFrontend/event/fellow.jsp";
		}
	}
	
	private String getAllRestaurant(HttpServletRequest req, HttpServletResponse res) {
		List<Restaurant> restaurantList = evtsvc.getAllRestaurant(new Restaurant());
        req.getSession().setAttribute("restaurantList", restaurantList);
      //保存選中的餐廳ID
		String[] selectedRest = req.getParameterValues("restchoice");
		if(selectedRest != null) {
			req.setAttribute("selectedRest", selectedRest);
			for(String item : selectedRest) {
				System.out.println(item);
			}
		}
		
		return "/frontstage/memberFrontend/vote/preparing_voting.jsp";
		
	}
	private String getCompositeQuery(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String[]> map = req.getParameterMap();
		if (map !=null) {
			List<Restaurant> restaurantList = evtsvc.getByCompositeQuery(map);
			req.setAttribute("restaurantList", restaurantList);
			
			//保存選中的餐廳ID
			String[] selectedRest = req.getParameterValues("restchoice");
			if(selectedRest != null) {
				for(String item : selectedRest) {
					System.out.println(item);
				}
				req.setAttribute("selectedRest", selectedRest);
			}
			
		}else {
			//保存選中的餐廳ID
			String[] selectedRest = req.getParameterValues("restchoice");
			if(selectedRest != null) {
				for(String item : selectedRest) {
					System.out.println(item);
				}
				req.setAttribute("selectedRest", selectedRest);
			}
			return "/frontstage/memberFrontend/vote/preparing_voting.jsp";
		}
	    return "/frontstage/memberFrontend/vote/preparing_voting.jsp";
}		
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		doPost(req,res);
	}
	
}
