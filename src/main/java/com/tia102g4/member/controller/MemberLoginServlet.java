package com.tia102g4.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tia102g4.member.service.MemberService;
import com.tia102g4.member.service.MemberServiceImpl;

@WebServlet("/memberLogin")
public class MemberLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 檢查 session 中是否有帳號
        String rememberedAccount = (String) request.getSession().getAttribute("rememberedAccount");
        if (rememberedAccount != null) {
            request.setAttribute("account", rememberedAccount);
            request.getSession().removeAttribute("rememberedAccount"); // 移除 session 中的帳號
        }
        request.getRequestDispatcher("/memberLogin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        boolean rememberMe = "on".equals(request.getParameter("rememberMe"));

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");

        try {
            if (memberService.authenticate(account, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("account", account);
                
             // 這裡假設有一個方法可以根據 account 獲取 memberId
                Long memberId = memberService.getMemberIdByAccount(account);
                session.setAttribute("memberId", memberId); // 確保設置 memberId

                if (rememberMe) {
                    // 如果勾選記住我，將帳號存入 session
                    session.setAttribute("rememberedAccount", account); 
                }

                out.write("{\"success\": true}");
            } else {
                // 登入失敗
                // 將 account 和 rememberMe 狀態傳回前端
                out.write("{\"success\": false, \"message\": \"帳號或密碼錯誤\", \"account\": \"" + account + "\", \"rememberMe\": " + rememberMe + "}"); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.write("{\"success\": false, \"message\": \"帳號未註冊\"}");
        }
    }
}

