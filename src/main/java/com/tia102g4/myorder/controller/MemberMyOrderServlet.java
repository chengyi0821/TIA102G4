package com.tia102g4.myorder.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

//import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tia102g4.event.model.Event;
import com.tia102g4.event.service.EventService;
import com.tia102g4.event.service.EventServiceImpl;
import com.tia102g4.member.model.Member;
//import com.tia102g4.member.service.MemberService;
//import com.tia102g4.member.service.MemberServiceImpl;
import com.tia102g4.myorder.model.MyOrder;
//import com.tia102g4.myorder.service.MailService;
import com.tia102g4.myorder.service.MyOrderService;
import com.tia102g4.myorder.service.MyOrderServiceImpl;
import com.tia102g4.rest.model.Restaurant;
//import com.tia102g4.rest.service.RestService;
//import com.tia102g4.rest.service.RestServiceImpl;
//import com.tia102g4.vote.service.VoteService;
//import com.tia102g4.vote.service.VoteServiceImpl;

@WebServlet("/myorder/membermyorder.do")
public class MemberMyOrderServlet extends HttpServlet {

	    private MyOrderService orderService;
	    private EventService eventService;
//	    private MemberService memberService;
//	    private RestService restService;
//	    private VoteService voteService;
//	    private MailService mailService;

	    @Override
	    public void init() throws ServletException {
	        orderService = new MyOrderServiceImpl();
	        eventService = new EventServiceImpl();
//	        memberService = new MemberServiceImpl();
//	        restService = new RestServiceImpl();
//	        voteService = new VoteServiceImpl();
	    }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		try {
			switch (action) {
			case "getOrderStatus1Mem":
				forwardPath = getOrderStatus1Member(req, res);
				break;
			case "getOrderStatus2Mem":
				forwardPath = getOrderStatus2Member(req, res);
				break;
			case "getOrderStatus3Mem":
				forwardPath = getOrderStatus3Member(req, res);
				break;
			case "updateOrderStatus2Mem":
				forwardPath = updateOrderStatus2Mem(req, res);
				break;
			case "loadOrderForEdit":
				forwardPath = loadOrderForEdit(req, res);
				break;
			case "updateOrder":
				forwardPath = updateOrder(req, res);
				break;
			case "addOrder":
                forwardPath = addOrder(req, res);
                break;
			default:
				forwardPath = "/frontstage/memberFrontend/myorder/member_order_index.jsp";
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

//	 =================================餐廳的訂單狀態1================================================

	private String getOrderStatus1Member(HttpServletRequest req, HttpServletResponse res) {
		Long memberId = 1L;
		List<MyOrder> orderList = orderService.getOrderStatus1Member(memberId);
		req.setAttribute("orderList", orderList);
		return "/frontstage/memberFrontend/myorder/member_orderStatus1.jsp";
	}
	
	private String getOrderStatus2Member(HttpServletRequest req, HttpServletResponse res) {
		Long memberId = 1L;
		List<MyOrder> orderList = orderService.getOrderStatus2Member(memberId);
		req.setAttribute("orderList", orderList);
		return "/frontstage/memberFrontend/myorder/member_orderStatus2.jsp";
	}
	
	private String getOrderStatus3Member(HttpServletRequest req, HttpServletResponse res) {
		Long memberId = 1L;
		List<MyOrder> orderList = orderService.getOrderStatus3Member(memberId);
		req.setAttribute("orderList", orderList);
		return "/frontstage/memberFrontend/myorder/member_orderStatus3.jsp";
	}
	

	// =================================member 取消訂單================================================

	private String updateOrderStatus2Mem(HttpServletRequest req, HttpServletResponse res) throws InterruptedException {
		String action = req.getParameter("action");
		Long memberId = 1L;

		if ("updateOrderStatus2Mem".equals(action)) {
			Long orderId = Long.parseLong(req.getParameter("orderId"));
			orderService.updateOrderStatus2Mem(orderId, "2", memberId);
			Thread.sleep(1500);
		}
		return getOrderStatus1Member(req, res);
	}

//	===============================================編輯訂單=======================================================
	 private String loadOrderForEdit(HttpServletRequest req, HttpServletResponse res) {
	        Long orderId = Long.parseLong(req.getParameter("orderId"));
	        MyOrder order = orderService.getMyOrderByOrderId(orderId);
	        req.setAttribute("order", order);
	        return "/frontstage/memberFrontend/myorder/member_editOrder.jsp";
	    }

	 private String updateOrder(HttpServletRequest req, HttpServletResponse res) throws InterruptedException, IOException {
	        Long orderId = Long.parseLong(req.getParameter("orderId"));
	        MyOrder order = orderService.getMyOrderByOrderId(orderId);

	        order.setReserDate(Date.valueOf(req.getParameter("reserDate")));
	        order.setReserTime(Time.valueOf(req.getParameter("reserTime") + ":00"));
	        order.setReserPeopleNumber(Integer.parseInt(req.getParameter("reserPeopleNumber")));
	        order.setReserNote(req.getParameter("reserNote"));

	        orderService.updateOrder(order);
	    
	        Thread.sleep(1500);
	        return getOrderStatus1Member(req, res);
	    }

	 
//	 private String updateOrder(HttpServletRequest req, HttpServletResponse res) throws InterruptedException, IOException, ServletException {
//		    Long orderId = Long.parseLong(req.getParameter("orderId"));
//		    MyOrder order = orderService.getMyOrderByOrderId(orderId);
//
//		    // 获取新的人数
//		    int newPeopleNumber = Integer.parseInt(req.getParameter("reserPeopleNumber"));
//
//		    // 获取 Event 对象和最大座位数
//		    Event event = order.getEvent();
//		    int maxSeat = event.getMaxSeat();
//
//		    // 检查新的人数是否超过最大座位数
//		    if (newPeopleNumber > maxSeat) {
//		        // 设置错误消息和orderId并重定向回表单页面
//		        req.setAttribute("errorMessage", "人數不能大於活動的最大座位數 (" + maxSeat + ")");
//		        
//		        // 将用户输入的数据保留，便于他们修改
//		        req.setAttribute("reserDate", req.getParameter("reserDate"));
//		        req.setAttribute("reserTime", req.getParameter("reserTime"));
//		        req.setAttribute("reserPeopleNumber", req.getParameter("reserPeopleNumber"));
//		        req.setAttribute("reserNote", req.getParameter("reserNote"));
//		        
//		        // 转发请求，保留 orderId 在 URL 中
//		        req.getRequestDispatcher("/myorder/membermyorder.do?action=loadOrderForEdit&orderId=" + orderId).forward(req, res);
//		        return null; // 停止后续处理
//		    }
//
//		    // 如果人数符合条件，继续更新订单
//		    order.setReserDate(Date.valueOf(req.getParameter("reserDate")));
//		    order.setReserTime(Time.valueOf(req.getParameter("reserTime") + ":00"));
//		    order.setReserPeopleNumber(newPeopleNumber);
//		    order.setReserNote(req.getParameter("reserNote"));
//
//		    // 更新订单
//		    orderService.updateOrder(order);
//
//		    // 模拟延迟
//		    Thread.sleep(1500);
//
//		    // 返回到订单状态页面
//		    return getOrderStatus1Member(req, res);
//		}


//	 ========================================add order===============================================
	 
	 private String addOrder(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		    MyOrder order = new MyOrder();
		    Restaurant restaurant = new Restaurant();
		    restaurant.setRestId(1L); 
		    
		    Event event = new Event();
//		    event.setEventno(20L); 
		    
		    Member member = new Member();
		    member.setMemberId(1L); 
		   
		    order.setMember(member);
		    order.setEvent(event);
		    order.setRestaurant(restaurant);
		    order.setMemberName("陳小明");
		    order.setPhone("09776555");
		    order.setOrderDate(new Timestamp(System.currentTimeMillis()));
		    order.setOrderStatus("1");
		    order.setReserDate(Date.valueOf("2024-08-09"));
		    order.setReserTime(Time.valueOf("18:00:00"));
		    order.setReserPeopleNumber(12);
		    order.setReserNote("aaaaaa");

		    try {
		        orderService.addOrder(order);
		        
//		        // 获取Session对象
//		        Session mailSession = (Session) getServletContext().getAttribute("mailSession");
//		        MailService mailService = new MailService(mailSession);
//		        mailService.sendEmail(order); // 发送邮件
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		        req.setAttribute("errorMsg", "订单创建失败");
		        return "/error.jsp";
		    }

		    req.setAttribute("order", order);
		    return getOrderStatus1Member(req, res);
		}

	 
//	 private String addOrder(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//	
//		    Long memberId = 1L; // Hardcoded memberId
//	        Long eventId = 1L;
//
////	        Event event = eventService.getEventById(eventId);
////	        Member member = memberService.getMemberById(memberId);
////	        Vote vote = voteService.getVoteById(eventId);
////	        Restaurant restaurant = restService.getRestById(vote.getRestaurant().getRestId());
//
////	        MyOrder order = new MyOrder();
////	        order.setMember(member);
////	        order.setEvent(event);
////	        order.setRestaurant(restaurant);
////	        order.setMemberName(event.getName());
////	        order.setPhone(member.getMobileNo());
////	        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
////	        order.setOrderStatus("1");
////	        order.setReserDate(event.getDate());
////	        order.setReserTime(event.getTime());
////	        order.setReserPeopleNumber(event.getSeat());
////	        order.setReserNote(req.getParameter("reserNote"));
//
//	        
//	        orderService.addOrder(order);
//	        
//	        String recipientEmail = "user-email@example.com"; // 替换为用户的实际邮箱地址
//	        String subject = "订单已完成";
//	        String body = "尊敬的用户，您的订单已成功完成。感谢您的使用！";
//	        
//	        EmailSender.sendEmail(recipientEmail, subject, body);
//
//	        req.setAttribute("order", order);
//	        return "/myorder/addOrder.jsp";
//		}
	 

//===================================================================================================================

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
