package com.tia102g4.member.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

@WebServlet("/member/logout.do")
public class MemberLogoutServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		switch (action) {
		case "MemberLogout":
			MemberLogout(req, res);
			break;
		}

		// 在所有操作之後設置 Content-Type 和字符編碼
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
	}

	private void MemberLogout(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		// 返回一個成功的 JSON 響應
		JsonObject jsonResponse = new JsonObject();
		jsonResponse.addProperty("status", "success");
		res.getWriter().write(jsonResponse.toString());
		res.setStatus(HttpServletResponse.SC_OK);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}

