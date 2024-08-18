package com.tia102g4.myorder.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
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
	    Long restId = ((Number) req.getSession().getAttribute("restId")).longValue();


	    PageInfo<MyOrder> pageInfo = orderService.getAllMyOrdersByRest(restId, currentPage, recordsPerPage);

	    req.getSession().setAttribute("orderList", pageInfo.getItems());
	    req.getSession().setAttribute("currentPage", pageInfo.getCurrentPage());
	    req.getSession().setAttribute("totalPages", pageInfo.getTotalPages());

	    return "/frontstage/restaurantFrontend/myorder/restaurant_listAllOrders.jsp";
	}


//	    ====================================屬於餐廳的復合查詢訂單=============================================
	private String getCompositeOrdersQuery(HttpServletRequest req, HttpServletResponse res) {
		Long restId = ((Number) req.getSession().getAttribute("restId")).longValue();

		Map<String, String[]> map = req.getParameterMap();
		List<MyOrder> orderList = orderService.getCompositeOrdersQueryByRestId(map, restId);

		if (orderList != null) {
			req.getSession().setAttribute("orderList", orderList);
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
	    Long restId = ((Number) req.getSession().getAttribute("restId")).longValue();

	    PageInfo<MyOrder> pageInfo = orderService.getOrderStatus1Rest(restId, currentPage, recordsPerPage);

	    req.getSession().setAttribute("orderList", pageInfo.getItems());
	    req.getSession().setAttribute("currentPage", pageInfo.getCurrentPage());
	    req.getSession().setAttribute("totalPages", pageInfo.getTotalPages());

	    return "/frontstage/restaurantFrontend/myorder/restaurant_orderStatus1.jsp";
	}

//	   //	 =================================餐廳的訂單狀態2================================================
	private String getOrderStatus2Rest(HttpServletRequest req, HttpServletResponse res) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		int recordsPerPage = 7;
		Long restId = ((Number) req.getSession().getAttribute("restId")).longValue();

		PageInfo<MyOrder> pageInfo = orderService.getOrderStatus2Rest(restId, currentPage, recordsPerPage);

		if (pageInfo != null) {
			req.getSession().setAttribute("orderList", pageInfo.getItems());
			req.getSession().setAttribute("currentPage", pageInfo.getCurrentPage());
			req.getSession().setAttribute("totalPages", pageInfo.getTotalPages());
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

		Long restId = ((Number) req.getSession().getAttribute("restId")).longValue();

		PageInfo<MyOrder> pageInfo = orderService.getOrderStatus3Rest(restId, currentPage, recordsPerPage);

		if (pageInfo != null) {
			req.getSession().setAttribute("orderList", pageInfo.getItems());
			req.getSession().setAttribute("currentPage", pageInfo.getCurrentPage());
			req.getSession().setAttribute("totalPages", pageInfo.getTotalPages());
		} else {
			req.setAttribute("error", "error");
		}

		return "/frontstage/restaurantFrontend/myorder/restaurant_orderStatus3.jsp";
	}

	private String updateOrderStatus2Rest(HttpServletRequest req, HttpServletResponse res) throws InterruptedException {
	    String action = req.getParameter("action");
	    Long restId = ((Number) req.getSession().getAttribute("restId")).longValue();

	    if ("updateOrderStatus2Rest".equals(action)) {
	        Long orderId = Long.parseLong(req.getParameter("orderId"));
	        orderService.updateOrderStatus2Rest(orderId, "2", restId);
	        Thread.sleep(1500);
	    }
	    return getOrderStatus1Rest(req, res); 
	}
	
	
	private String updateOrderStatus3Rest(HttpServletRequest req, HttpServletResponse res) throws InterruptedException {
		 String action = req.getParameter("action");
		 Long restId = ((Number) req.getSession().getAttribute("restId")).longValue();

		    if ("updateOrderStatus3Rest".equals(action)) {
		        Long orderId = Long.parseLong(req.getParameter("orderId"));
		        MyOrder order = orderService.getMyOrderByOrderId(orderId);

		        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

		        Calendar reservationCalendar = Calendar.getInstance();
		        reservationCalendar.setTime(order.getReserDate());
		        reservationCalendar.set(Calendar.HOUR_OF_DAY, order.getReserTime().toLocalTime().getHour());
		        reservationCalendar.set(Calendar.MINUTE, order.getReserTime().toLocalTime().getMinute());
		        reservationCalendar.set(Calendar.SECOND, 0); 

		        Timestamp reservationTimestamp = new Timestamp(reservationCalendar.getTimeInMillis());

		        if (!reservationTimestamp.after(currentTimestamp)) {
		            orderService.updateOrderStatus3Rest(orderId, "3", restId);
		            req.setAttribute("successMsg", "已完成這筆訂單");
		            return getOrderStatus1Rest(req, res);
		        } else {
		        	req.setAttribute("errorMsg", "訂位時間還沒到，無法完成該筆訂單");
		            return getOrderStatus1Rest(req, res); 
		        }
		    }
		    return getOrderStatus1Rest(req, res);
	}
//===================================================================================================================

	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
