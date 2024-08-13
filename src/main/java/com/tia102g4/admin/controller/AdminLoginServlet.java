package com.tia102g4.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tia102g4.admin.model.Admin;
import com.tia102g4.admin.service.AdminService;
import com.tia102g4.admin.service.AdminServiceImpl;

@WebServlet("/login")
public class AdminLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AdminService adminService = new AdminServiceImpl();
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 如果訪問 /login 時是 GET 請求，重定向到登入頁面
        response.sendRedirect("login.jsp");
      
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");

        try {
        	
            // 日志输出，确认收到的请求参数
            System.out.println("Received login request for account: " + account);
            
            Admin admin = adminService.authenticate(account, password);
         
                
            if (admin != null) {
                System.out.println("Admin found: " + admin.getAccount());
                HttpSession session = request.getSession();
                session.setAttribute("admin", admin);

                if ("chugether".equals(password)) {
                    out.write("{\"success\": true, \"changePassword\": true}");
                } else {
                    out.write("{\"success\": true, \"changePassword\": false}");
                }
            } else {
                out.write("{\"success\": false, \"message\": \"帳號或密碼錯誤\"}");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.write("{\"success\": false, \"message\": \"登入失敗，請稍後再試\"}");
        }
    }
}
