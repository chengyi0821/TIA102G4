package com.tia102g4.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/*" })
public class MemberLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        // 登入頁面的 URI
        String loginURI = req.getContextPath() + "/frontstage/memberFrontend/member/memberlogin.jsp";

        // 檢查用戶是否已經登入
        boolean loggedIn = (session != null && session.getAttribute("memberId") != null);

        // 檢查當前請求是否為登入頁面請求
        boolean loginRequest = req.getRequestURI().equals(loginURI);

        // 如果用戶已經登入，或者當前請求是登入頁面請求，允許請求繼續
        if (loggedIn || loginRequest) {
            chain.doFilter(request, response);
        } else {
            // 否則，重定向到登入頁面
            res.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {
    }
}

