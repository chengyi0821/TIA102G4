package com.tia102g4.blacklist.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tia102g4.blacklist.model.BlackList;
import com.tia102g4.blacklist.service.BlackListService;
import com.tia102g4.blacklist.service.BlackListServiceImpl;
import com.tia102g4.member.model.Member;
import com.tia102g4.rest.model.Restaurant;


@WebServlet("/blacklist/blacklist.do")
public class BlackListServlet extends HttpServlet{

	private BlackListService blackListService;

	@Override
	public void init() throws ServletException {
		blackListService = new BlackListServiceImpl();
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    String action = req.getParameter("action");
	    String forwardPath = "";

	    try {
	        switch (action) {
	            case "getAll":
	                forwardPath = getAllBlackList(req, res);
	                break;
	            case "compositeQuery":
	                forwardPath = getCompositeBlackListQuery(req, res);
	                break;
	            case "delete":
	                forwardPath = deleteBlackList(req, res);
	                break;
	            case "add":
	                forwardPath = addBlackList(req, res);
	                break;
	            default:
	                forwardPath = "/index.jsp";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        req.setAttribute("errorMsg", "An error occurred: " + e.getMessage());
	        forwardPath = "/error.jsp";
	    }

	    res.setContentType("text/html; charset=UTF-8");
	    RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
	    dispatcher.forward(req, res);
	}
	
	private String getAllBlackList(HttpServletRequest req, HttpServletResponse res) {
	    List<BlackList> blackListList = blackListService.getAllBlackList();
	    req.setAttribute("blackListList", blackListList);
	    return "/frontstage/restaurantFrontend/blacklist/listAllBlackList.jsp";
	}
	

	    private String getCompositeBlackListQuery(HttpServletRequest req, HttpServletResponse res) {
	        Map<String, String[]> map = req.getParameterMap();

	        if (map != null) {
	            List<BlackList> blackListList = blackListService.getBlackListByCompositeQuery(map);
	            req.setAttribute("blackListList", blackListList);
	        } else {
	            return "/index.jsp";
	        }
	        return "/frontstage/restaurantFrontend/blacklist/listAllBlackList.jsp";
	    }

	    
	    private String deleteBlackList(HttpServletRequest req, HttpServletResponse res) throws InterruptedException {
			Long blackListId = Long.valueOf(req.getParameter("blackListId"));
			blackListService.deleteBlackList(blackListId);
			Thread.sleep(2200);
			return "/blacklist/blacklist.do?action=getAll";
		}
	    
	    private String addBlackList(HttpServletRequest req, HttpServletResponse res) {
	    	 String memberIdStr = req.getParameter("memberId");
	    	    String restaurantIdStr = req.getParameter("restaurantId");

	    	    if (memberIdStr == null || memberIdStr.isEmpty() || restaurantIdStr == null || restaurantIdStr.isEmpty()) {
	    	        // 处理缺少参数或参数为空的情况
	    	        req.setAttribute("error", "Member ID or Restaurant ID is missing or empty.");
	    	        return "/error.jsp"; // 返回一个错误页面或显示错误信息
	    	    }

	    	    Long memberId;
	    	    Long restaurantId;
	    	    try {
	    	        memberId = Long.parseLong(memberIdStr);
	    	        restaurantId = Long.parseLong(restaurantIdStr);
	    	    } catch (NumberFormatException e) {
	    	        // 处理解析错误的情况
	    	        req.setAttribute("error", "Invalid Member ID or Restaurant ID format.");
	    	        return "/error.jsp"; // 返回一个错误页面或显示错误信息
	    	    }

	    	    if (blackListService.isMemberInBlackList(memberId)) {
	    	        // 成员已经在黑名单中
	    	        req.setAttribute("message", "此用戶已在黑名單列表！");
	    	        return getAllBlackList(req, res); // 返回显示订单页面，同时显示提示信息
	    	    }

	    	    Member member = new Member();
	    	    member.setMemberId(memberId);

	    	    Restaurant restaurant = new Restaurant();
	    	    restaurant.setRestId(restaurantId);

	    	    BlackList blackList = new BlackList();
	    	    blackList.setMember(member);
	    	    blackList.setRestaurant(restaurant);

	    	    BlackList savedBlackList = blackListService.addBlackList(blackList);

	    	    if (savedBlackList == null) {
	    	        // 处理保存失败的情况
	    	        req.setAttribute("error", "Failed to add to blacklist.");
	    	        return "/error.jsp"; // 返回一个错误页面或显示错误信息
	    	    }

	    	    req.setAttribute("success", "新增成功"); // 设置成功消息
	    	    return getAllBlackList(req, res);
	    }

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	        doPost(req, res);
	    }
	}