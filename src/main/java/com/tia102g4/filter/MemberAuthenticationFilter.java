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

import com.tia102g4.member.service.MemberService;
import com.tia102g4.member.service.MemberServiceImpl;

@WebFilter(urlPatterns = "/member")
public class MemberAuthenticationFilter implements Filter {

    private MemberService memberService = new MemberServiceImpl(); // 初始化 MemberService 实例

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
        
        System.out.println("測試測試01: URI = " + uri);
        
        // 如果請求的是登錄或註冊相關的資源，直接放行
        if (uri.endsWith("memberLogin.jsp") || uri.endsWith("memberRegister.jsp") || uri.endsWith("memberForgotPassword.jsp") || uri.endsWith("memberResetPassword.jsp") || uri.endsWith("/memberLogin") || uri.endsWith("/memberRegister") || uri.endsWith("/memberForgotPassword") || uri.endsWith("/memberResetPassword")) {
            chain.doFilter(request, response);
            return;
        }

        // 檢查用戶是否已經登錄
        if (session != null && session.getAttribute("account") != null && session.getAttribute("memberId") != null) {
            // 用戶已經登錄，直接放行
            System.out.println("測試測試02: 用戶已登錄");

            chain.doFilter(request, response);
        } else {
            // 用戶未登錄，檢查記住我功能的 cookie
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("account".equals(cookie.getName()) && cookie.getValue() != null && !cookie.getValue().isEmpty()) {
                        // 找到記住我的 cookie，模擬登入
                        System.out.println("測試測試03: 找到記住我的 cookie");
                        session = req.getSession(true);
                        session.setAttribute("account", cookie.getValue());
                        try {
                            Long memberId = memberService.getMemberIdByAccount(cookie.getValue()); // 新增代码
                            session.setAttribute("memberId", memberId); // 新增代码
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        chain.doFilter(request, response);
                        return;
                    }
                }
            }
            System.out.println("測試測試04: 用戶未登錄，重定向到登錄頁面");
            // 用戶未登錄，重定向到登錄頁面
            res.sendRedirect("memberLogin.jsp");
        }
    }

    @Override
    public void destroy() {
    }
}
