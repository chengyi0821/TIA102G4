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
import com.tia102g4.myorder.model.MyOrder;
import com.tia102g4.myorder.service.MyOrderService;
import com.tia102g4.myorder.service.MyOrderServiceImpl;
import com.tia102g4.rest.model.Restaurant;

@WebServlet("/blacklist/blacklist.do")
public class BlackListServlet extends HttpServlet {

	private BlackListService blackListService;
    private MyOrderService orderService;

	@Override
	public void init() throws ServletException {
		blackListService = new BlackListServiceImpl();
		  orderService = new MyOrderServiceImpl();
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
		Long restId = ((Number) req.getSession().getAttribute("restId")).longValue();
		
		List<BlackList> blackListList = blackListService.getAllBlackList(restId);
		req.getSession().setAttribute("blackListList", blackListList);
		return "/frontstage/restaurantFrontend/blacklist/listAllBlackList.jsp";
	}

	private String getCompositeBlackListQuery(HttpServletRequest req, HttpServletResponse res) {
		Long restId = ((Number) req.getSession().getAttribute("restId")).longValue();
	    Map<String, String[]> map = req.getParameterMap();
	    
	    if (map != null) {
	        List<BlackList> blackListList = blackListService.getBlackListByCompositeQuery(map, restId);
	        req.getSession().setAttribute("blackListList", blackListList);
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
	    String orderIdStr = req.getParameter("orderId");
	    Long restId = ((Number) req.getSession().getAttribute("restId")).longValue();

	    if (orderIdStr == null || orderIdStr.isEmpty()) {
	    	req.setAttribute("orderIdError", "錯誤");
	        return "/error.jsp";
	    }

	    Long orderId;
	    try {
	        orderId = Long.parseLong(orderIdStr);
	    } catch (NumberFormatException e) {
	    	req.getSession().setAttribute("orderIdError", "錯誤");
	        return "/error.jsp";
	    }

	
	    MyOrder order = orderService.getMyOrderByOrderId1(orderId, restId);
	    if (order == null) {
	    	req.setAttribute("orderIdError", "訂單編號錯誤");
	        return getAllBlackList(req, res);
	    }

	    Long memberId = order.getMember().getMemberId();

	    if (blackListService.isMemberInBlackList(memberId, restId)) {
	    	req.setAttribute("message", "此用戶已在黑名單列表！");
	        return getAllBlackList(req, res);
	    }

	    Member member = order.getMember();
	    Restaurant restaurant = order.getRestaurant();

	    BlackList blackList = new BlackList();
	    blackList.setMember(member);
	    blackList.setRestaurant(restaurant);

	    BlackList savedBlackList = blackListService.addBlackList(blackList);

	    if (savedBlackList == null) {
	    	req.getSession().setAttribute("error", "Failed to add to blacklist.");
	        return "/error.jsp";
	    }

	    req.setAttribute("success", "新增成功");
	    return getAllBlackList(req, res);
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}