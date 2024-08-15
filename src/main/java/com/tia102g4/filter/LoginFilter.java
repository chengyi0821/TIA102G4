package com.tia102g4.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginFilter implements Filter {
	
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {	
    }
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		
		String loginURI = req.getContextPath() +"/login.jsp";
		
		boolean loggedIn =(session != null && session.getAttribute("loggedInMember") != null);
		boolean loginRequest = req.getRequestURI().equals(loginURI);
		
		if(loggedIn || loginRequest) {
			chain.doFilter(request, response);
		}else {
			res.sendRedirect(loginURI);
		}
		
	}
	
        @Override
        public void destroy() {
        	
        }
	
		
	}

