package com.tia102g4.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tia102g4.member.service.MemberService;
import com.tia102g4.member.service.MemberServiceImpl;
import com.tia102g4.member.service.MemberService;

@WebServlet("/memberResetPassword")
public class MemberResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        try {
			if (memberService.isValidResetToken(token)) {
			    request.setAttribute("resetToken", token);
			    request.getRequestDispatcher("/memberResetPassword.jsp").forward(request, response);
			} else {
			    response.getWriter().write("無效或已過期的 token");
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		} catch (ServletException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("resetToken");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");

        if (!newPassword.equals(confirmPassword)) {
            out.write("{\"success\": false, \"message\": \"密碼不匹配\"}");
            return;
        }

        try {
            if (memberService.resetPassword(token, newPassword)) {
                out.write("{\"success\": true}");
            } else {
                out.write("{\"success\": false, \"message\": \"無效或已過期的 token\"}");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.write("{\"success\": false, \"message\": \"資料庫錯誤\"}");
        }
    }
}
