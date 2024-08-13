package com.tia102g4.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tia102g4.admin.model.Admin;
import com.tia102g4.admin.service.AdminService;
import com.tia102g4.admin.service.AdminServiceImpl;

@WebFilter(urlPatterns = "/admin")
public class AdminAuthenticationFilter implements Filter {

    private AdminService adminService = new AdminServiceImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);

        // 如果請求的是登錄或相關的資源，直接放行
        if (uri.endsWith("login.jsp") || uri.endsWith("resetPassword.jsp") || uri.endsWith("/login") || uri.endsWith("/resetPassword")) {
            chain.doFilter(request, response);
            return;
        }

        // 檢查用戶是否已經登錄
        if (session != null && session.getAttribute("admin") != null) {
            chain.doFilter(request, response);
        } else {
            // 用戶未登錄，檢查記住我功能的 cookie
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("account".equals(cookie.getName()) && cookie.getValue() != null && !cookie.getValue().isEmpty()) {
                        try {
                            Admin admin = adminService.findAdminByAccount(cookie.getValue());
                            if (admin != null) {
                                session = req.getSession(true);
                                session.setAttribute("admin", admin);
                                chain.doFilter(request, response);
                                return;
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            res.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }

    @Override
    public void destroy() {
    }
}
