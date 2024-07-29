package com.tia102g4.event.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		case "reduce":
			forwardPath = reduceSeat(req,res);
			break;
		case "change":
			forwardPath = changeLeader(req,res);
			break;
		default:
			forwardPath = "/index.jsp";
		}
		
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}
	
	private String addEvent(HttpServletRequest req, HttpServletResponse res) {
		return "/event/info.jsp";
	}
	
	private String reduceSeat(HttpServletRequest req, HttpServletResponse res) {
		return "/event/info.jsp";
	}
	
	private String changeLeader(HttpServletRequest req, HttpServletResponse res) {
		return "/event/info.jsp";
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		doPost(req,res);
	}
	
}
