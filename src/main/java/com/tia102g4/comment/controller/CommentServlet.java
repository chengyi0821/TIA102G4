package com.tia102g4.comment.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tia102g4.comment.model.Comment;
import com.tia102g4.comment.service.CommentService;
import com.tia102g4.comment.service.CommentServiceImpl;

public class CommentServlet extends HttpServlet {
	private CommentService comsvc;

	@Override
	public void init() throws ServletException {
		comsvc = new CommentServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
		case "add":
			forwardPath = addComment(req, res);
			break;
		case "update":
			forwardPath = updateComment(req, res);
			break;
		case "find":
			forwardPath = getCommentByCompositeQuery(req, res);
			break;
		default:
			forwardPath = "/index.jsp";
		}
		
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}

	//撰寫評論
	private String addComment(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//接收請求參數
		
		//轉交資料
		
		//導向網頁
		return"";
	}

	//更新評論
	private String updateComment(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//接收請求參數
		
		//轉交資料
				
		//導向網頁
		return"";
	}
	
	//搜尋餐廳評論
	private String getCommentByCompositeQuery(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		Map<String, String[]> map = req.getParameterMap();
		
		if(map != null) {
			List<Comment> comList = comsvc.getCommentByCompositeQuery(map);
			req.setAttribute("comList", comList);
		}else {
			return"";
		}
		return"";
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
