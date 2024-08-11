package com.tia102g4.myorder.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.tia102g4.myorder.model.MyOrder;
import com.tia102g4.myorder.model.PageInfo;
import com.tia102g4.myorder.service.MyOrderService;
import com.tia102g4.myorder.service.MyOrderServiceImpl;

@WebServlet("/myorder/restmyorder.do")
public class RestMyOrderServlet extends HttpServlet {

	private MyOrderService orderService;

	@Override
	public void init() throws ServletException {
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
				forwardPath = getAllOrders(req, res);
				break;
			case "compositeQuery":
				forwardPath = getCompositeOrdersQuery(req, res);
				break;
			case "getOrderStatus1Rest":
				forwardPath = getOrderStatus1Rest(req, res);
				break;
			case "getOrderStatus2Rest":
				forwardPath = getOrderStatus2Rest(req, res);
				break;
			case "getOrderStatus3Rest":
				forwardPath = getOrderStatus3Rest(req, res);
				break;
			case "updateOrderStatus2Rest":
				forwardPath = updateOrderStatus2Rest(req, res);
				break;
	        case "updateOrderStatus3Rest":
	            forwardPath = updateOrderStatus3Rest(req, res);
	            break;
			default:
				forwardPath = "/frontstage/restaurantFrontend/myorder/restaurant_order_index.jsp";
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

//	    ===============================屬於餐廳所有訂單============================================
	
	private String getAllOrders(HttpServletRequest req, HttpServletResponse res) {
	    String page = req.getParameter("page");
	    int currentPage = (page == null) ? 1 : Integer.parseInt(page);
	    int recordsPerPage = 7; 
//	    Long restId = ((Number) req.getSession().getAttribute("restId")).longValue();
	   
	    Long restId = 1L; 

	    PageInfo<MyOrder> pageInfo = orderService.getAllMyOrdersByRest(restId, currentPage, recordsPerPage);

	    req.setAttribute("orderList", pageInfo.getItems());
	    req.setAttribute("currentPage", pageInfo.getCurrentPage());
	    req.setAttribute("totalPages", pageInfo.getTotalPages());

	    return "/frontstage/restaurantFrontend/myorder/restaurant_listAllOrders.jsp";
	}


//	    ====================================屬於餐廳的復合查詢訂單=============================================
	private String getCompositeOrdersQuery(HttpServletRequest req, HttpServletResponse res) {
		// 測試
		Long restId = 1L;

		Map<String, String[]> map = req.getParameterMap();
		List<MyOrder> orderList = orderService.getCompositeOrdersQueryByRestId(map, restId);

		if (orderList != null) {
			req.setAttribute("orderList", orderList);
		} else {
			req.setAttribute("error", "error");
		}

		return "/frontstage/restaurantFrontend/myorder/restaurant_compositeOrder.jsp";
	}

//	 =================================餐廳的訂單狀態1================================================

	private String getOrderStatus1Rest(HttpServletRequest req, HttpServletResponse res) {
	    String page = req.getParameter("page");
	    int currentPage = (page == null) ? 1 : Integer.parseInt(page);
	    int recordsPerPage = 7;
	    Long restId = 1L; 

	    PageInfo<MyOrder> pageInfo = orderService.getOrderStatus1Rest(restId, currentPage, recordsPerPage);

	    req.setAttribute("orderList", pageInfo.getItems());
	    req.setAttribute("currentPage", pageInfo.getCurrentPage());
	    req.setAttribute("totalPages", pageInfo.getTotalPages());

	    return "/frontstage/restaurantFrontend/myorder/restaurant_orderStatus1.jsp";
	}

//	   //	 =================================餐廳的訂單狀態2================================================
	private String getOrderStatus2Rest(HttpServletRequest req, HttpServletResponse res) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		int recordsPerPage = 7;

		// 測試
		Long restId = 1L;

		PageInfo<MyOrder> pageInfo = orderService.getOrderStatus2Rest(restId, currentPage, recordsPerPage);

		if (pageInfo != null) {
			req.setAttribute("orderList", pageInfo.getItems());
			req.setAttribute("currentPage", pageInfo.getCurrentPage());
			req.setAttribute("totalPages", pageInfo.getTotalPages());
		} else {
			req.setAttribute("error", "error");
		}

		return "/frontstage/restaurantFrontend/myorder/restaurant_orderStatus2.jsp";
	}

	// =================================餐廳的訂單狀態3================================================
	private String getOrderStatus3Rest(HttpServletRequest req, HttpServletResponse res) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		int recordsPerPage = 7;

		// 測試
		Long restId = 1L;

		PageInfo<MyOrder> pageInfo = orderService.getOrderStatus3Rest(restId, currentPage, recordsPerPage);

		if (pageInfo != null) {
			req.setAttribute("orderList", pageInfo.getItems());
			req.setAttribute("currentPage", pageInfo.getCurrentPage());
			req.setAttribute("totalPages", pageInfo.getTotalPages());
		} else {
			req.setAttribute("error", "error");
		}

		return "/frontstage/restaurantFrontend/myorder/restaurant_orderStatus3.jsp";
	}

	private String updateOrderStatus2Rest(HttpServletRequest req, HttpServletResponse res) throws InterruptedException {
	    String action = req.getParameter("action");
	    Long restId = 1L;

	    if ("updateOrderStatus2Rest".equals(action)) {
	        Long orderId = Long.parseLong(req.getParameter("orderId"));
	        orderService.updateOrderStatus2Rest(orderId, "2", restId);
	        Thread.sleep(1500);
	    }
	    return getOrderStatus1Rest(req, res); 
	}
	
	private String updateOrderStatus3Rest(HttpServletRequest req, HttpServletResponse res) throws InterruptedException {
	    String action = req.getParameter("action");
	    Long restId = 1L; 

	    if ("updateOrderStatus3Rest".equals(action)) {
	        Long orderId = Long.parseLong(req.getParameter("orderId"));
	        orderService.updateOrderStatus3Rest(orderId, "3", restId);
	        Thread.sleep(1500);
	    }
	    return getOrderStatus1Rest(req, res);
	}
//===================================================================================================================

	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
