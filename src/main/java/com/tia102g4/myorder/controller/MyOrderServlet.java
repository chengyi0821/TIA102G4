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

@WebServlet("/myorder/myorder.do")
public class MyOrderServlet extends HttpServlet {

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
            case "getOrderStatus1":
                forwardPath = getOrderStatus1(req, res);
                break;
            case "getOrderStatus2":
                forwardPath = getOrderStatus2(req, res);
                break;
            case "getOrderStatus3":
                forwardPath = getOrderStatus3(req, res);
                break;
            case "getOrderId":
                forwardPath = getMyOrderByOrderId(req, res);
                break;
            case "getOrderId1":
                forwardPath = getMyOrderByOrderId1(req, res);
                break;
            case "updateOrderStatus2":
                forwardPath = updateOrderStatus2(req, res);
                break;
            case "updateOrderStatus3":
                forwardPath = updateOrderStatus3(req, res);
                break;
            default:
                forwardPath = "/frontstage/backend/myorder/admin_order_index.jsp";
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

    private String getAllOrders(HttpServletRequest req, HttpServletResponse res) {
        String page = req.getParameter("page");
        int currentPage = (page == null) ? 1 : Integer.parseInt(page);
        int recordsPerPage = 7;

        PageInfo<MyOrder> pageInfo = orderService.getAllMyOrders(currentPage, recordsPerPage);

        req.setAttribute("orderList", pageInfo.getItems());
        req.setAttribute("currentPage", pageInfo.getCurrentPage());
        req.setAttribute("totalPages", pageInfo.getTotalPages());

        return "/frontstage/backend/myorder/admin_listAllOrders.jsp";
    }

    private String getCompositeOrdersQuery(HttpServletRequest req, HttpServletResponse res) {
        Map<String, String[]> map = req.getParameterMap();

        if (map != null) {
            List<MyOrder> orderList = orderService.getMyOrdersByCompositeQuery(map);
            req.setAttribute("orderList", orderList);
        } else {
            return "/admin_order_index.jsp";
        }
        return "/frontstage/backend/myorder/admin_compositeOrder.jsp";
    }

    private String getMyOrderByOrderId(HttpServletRequest req, HttpServletResponse res) {
        Long orderId = Long.parseLong(req.getParameter("orderId"));
        MyOrder myOrder = orderService.getMyOrderByOrderId(orderId);
        if (myOrder != null) {
        	req.getSession().setAttribute("myOrder", myOrder);
        }
        return "/frontstage/restaurantFrontend/blacklist/listAllBlackList.jsp";
    }
    
    private String getMyOrderByOrderId1(HttpServletRequest req, HttpServletResponse res) {
 	   Long orderId = Long.parseLong(req.getParameter("orderId"));
 	   Long restId = ((Number) req.getSession().getAttribute("restId")).longValue();

 	    MyOrder myOrder = orderService.getMyOrderByOrderId1(orderId, restId);
 	    if (myOrder != null) {
 	    	req.setAttribute("myOrder", myOrder);
 	    } else {
 	    	req.setAttribute("orderIdError", "訂單編號錯誤");
 	    }
 	    return "/frontstage/restaurantFrontend/blacklist/listAllBlackList.jsp";
 }


    private String getOrderStatus1(HttpServletRequest req, HttpServletResponse res) {
        String page = req.getParameter("page");
        int currentPage = (page == null) ? 1 : Integer.parseInt(page);
        int recordsPerPage = 7;

        PageInfo<MyOrder> pageInfo = orderService.getOrderStatus1(currentPage, recordsPerPage);

        req.setAttribute("orderList", pageInfo.getItems());
        req.setAttribute("currentPage", pageInfo.getCurrentPage());
        req.setAttribute("totalPages", pageInfo.getTotalPages());

        return "/frontstage/backend/myorder/admin_orderStatus1.jsp";
    }

    private String getOrderStatus2(HttpServletRequest req, HttpServletResponse res) {
        String page = req.getParameter("page");
        int currentPage = (page == null) ? 1 : Integer.parseInt(page);
        int recordsPerPage = 7;

        PageInfo<MyOrder> pageInfo = orderService.getOrderStatus2(currentPage, recordsPerPage);

        req.setAttribute("orderList", pageInfo.getItems());
        req.setAttribute("currentPage", pageInfo.getCurrentPage());
        req.setAttribute("totalPages", pageInfo.getTotalPages());

        return "/frontstage/backend/myorder/admin_orderStatus2.jsp";
    }
    
    private String getOrderStatus3(HttpServletRequest req, HttpServletResponse res) {
        String page = req.getParameter("page");
        int currentPage = (page == null) ? 1 : Integer.parseInt(page);
        int recordsPerPage = 7;

        PageInfo<MyOrder> pageInfo = orderService.getOrderStatus3(currentPage, recordsPerPage);

        req.setAttribute("orderList", pageInfo.getItems());
        req.setAttribute("currentPage", pageInfo.getCurrentPage());
        req.setAttribute("totalPages", pageInfo.getTotalPages());

        return "/frontstage/backend/myorder/admin_orderStatus3.jsp";
    }

    private String updateOrderStatus2(HttpServletRequest req, HttpServletResponse res) throws InterruptedException{
        String action = req.getParameter("action");
        if ("updateOrderStatus2".equals(action)) {
            Long orderId = Long.parseLong(req.getParameter("orderId"));
            orderService.updateOrderStatus(orderId, "2");
            Thread.sleep(1500);
        }
        return getOrderStatus1(req, res);
    }
    
    private String updateOrderStatus3(HttpServletRequest req, HttpServletResponse res) throws InterruptedException {
    	 String action = req.getParameter("action");

		    if ("updateOrderStatus3".equals(action)) {
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
		            orderService.updateOrderStatus3(orderId, "3");
		            req.setAttribute("successMsg", "已完成這筆訂單");
		            return getOrderStatus1(req, res);
		        } else {
		            req.setAttribute("errorMsg", "訂位時間還沒到，無法完成該筆訂單");
		            return getOrderStatus1(req, res); 
		        }
		    }
		    return getOrderStatus1(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }
}
