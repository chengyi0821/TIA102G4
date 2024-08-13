package com.tia102g4.vote.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tia102g4.vote.service.VoteService;
import com.tia102g4.vote.service.VoteServiceImpl;

@WebServlet("/vote/vote.do")
public class VoteServlet extends HttpServlet{
	
	private VoteService votsvc;
	
	@Override
	public void init() throws ServletException{
		votsvc = new VoteServiceImpl();
	}
    
	protected void dopost(HttpServletRequest req, HttpServletResponse res)
	       throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch(action){
		case "add":
			forwardPath = addVote(req,res);
			break;
		case "tally":
			forwardPath = tallyVote(req,res);
			break;
		case "show":
			forwardPath = showVote(req,res);
			break;
		default:
			forwardPath = "/index.jsp";
		}
		
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
		
	}
	private String addVote(HttpServletRequest req, HttpServletResponse res) {
		return "/vote/info.jsp";
	}
	private String tallyVote(HttpServletRequest req, HttpServletResponse res) {	
		return "/vote/info.jsp";
	}

	private String showVote(HttpServletRequest req, HttpServletResponse res) {
		return "/vote/info.jsp";
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			 throws ServletException, IOException{
		doPost(req,res);
	}
	
}

