package com.tia102g4.room.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tia102g4.member.model.Member;
import com.tia102g4.room.model.Room;
import com.tia102g4.room.service.Room1Service;
import com.tia102g4.room.service.Room1ServiceImp1;

@WebServlet("/room/room1.do")
public class Room1Servlet extends HttpServlet {
    
	private Room1Service room1Service;
	
	@Override
	public void init() throws ServletException {
		room1Service = new Room1ServiceImp1();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   req.setCharacterEncoding("UTF-8");
	   String action = req.getParameter("action");
	   String forwardPath = "";
	   System.out.println("Room1Servlet is accessed via GET method");
       res.getWriter().write("GET method in Room1Servlet");
	   
	   switch (action){
	   case "createRoom":
		   forwardPath = createRoom(req, res);
		   break;
	   case "validateInviteCode":	   
		   forwardPath = validateInviteCode(req, res);
		   break;
	   default:
		   forwardPath ="/index.jsp";
		   break;
		      
	   }
	   res.setContentType("text/html; charset=UTF-8");
	   RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
	   dispatcher.forward(req, res);
	}

	private String createRoom(HttpServletRequest req, HttpServletResponse res) {
		String memberId = req.getParameter("memberId");
		String eventId = req.getParameter("eventId");
		Timestamp estTime = Timestamp.valueOf(req.getParameter("estTime"));
		Timestamp joinTime =Timestamp.valueOf(req.getParameter("joinTime"));
		Boolean status = Boolean.valueOf(req.getParameter("status"));
		
		Room room = new Room();
		room.setEstTime(estTime);
		room.setJoinTime(joinTime);
		room.setStatus(status);
		
//		Member member = new Member();
//		member.setMemberId(Long.parseLong(memberId));
//		room.setMember(member);
		
//		Event event = new Event();
//		event.setEventId(Long.parseLong(eventId));
//		room.setEvent(event);
		
		Long roomId =room1Service.createRoom(room);
		req.setAttribute("roomId", roomId);
		
		return "/room/Room2.jsp";
		
	}

	private String validateInviteCode(HttpServletRequest req, HttpServletResponse res) {
		String inviteCode = req.getParameter("inviteCode");
		boolean isValid = room1Service.InviteCodeValid(inviteCode);
		req.setAttribute("isValid", isValid);
		
	    return "/Room1.jsp"; 
	    }

	
    
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
        System.out.println("Room1Servlet is accessed via POST method");
        res.getWriter().write("POST method in Room1Servlet");
    }
}


	

