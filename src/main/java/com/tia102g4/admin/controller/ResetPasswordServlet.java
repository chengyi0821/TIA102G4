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

@WebServlet("/resetPassword")
public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminService adminService = new AdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 檢查使用者是否已登入
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");

        if (admin == null) {
            // 如果未登入，重定向至登入頁面
            response.sendRedirect("login.jsp");
            return;
        }

        // 如果已登入，顯示重設密碼頁面
        request.getRequestDispatcher("/resetPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");

        if (admin == null) {
            response.sendRedirect("/login.jsp");
            return;
        }

        String newPassword = request.getParameter("newPassword");

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");

        try {
            admin.setPassword(newPassword);
            adminService.updateAdmin(admin);
            session.setAttribute("admin", admin);
            out.write("{\"success\": true}");
        } catch (SQLException e) {
            e.printStackTrace();
            out.write("{\"success\": false, \"message\": \"重設密碼失敗，請稍後再試\"}");
        }
    }
}
