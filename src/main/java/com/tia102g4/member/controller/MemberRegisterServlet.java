package com.tia102g4.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.tia102g4.member.service.MemberService;
import com.tia102g4.member.service.MemberServiceImpl;

@WebServlet("/memberRegister")
@MultipartConfig
public class MemberRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/memberRegister.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 檢查和獲取所有參數
		String name = request.getParameter("name");
		Boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String mobileNo = request.getParameter("mobileNo");
		Timestamp regisDate = new Timestamp(System.currentTimeMillis());
		Boolean accStatus = true; // 設置默認帳號狀態為 true（啟用）

		// 處理圖片上傳
		Part filePart = request.getPart("sticker");
		byte[] sticker = null;
		if (filePart != null) {
			InputStream fileContent = filePart.getInputStream();
			sticker = fileContent.readAllBytes();
		}

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");

		try {
			if (memberService.registerMember(name, gender, account, password, mobileNo, email, sticker, regisDate,
					accStatus)) {
				out.write("{\"success\": true}");
			} else {
				out.write("{\"success\": false, \"message\": \"用戶已存在\"}");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			out.write("{\"success\": false, \"message\": \"資料庫錯誤\"}");
		}
	}
}
