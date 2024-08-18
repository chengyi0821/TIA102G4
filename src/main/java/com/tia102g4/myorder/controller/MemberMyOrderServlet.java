package com.tia102g4.myorder.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tia102g4.blacklist.service.BlackListService;
import com.tia102g4.blacklist.service.BlackListServiceImpl;
import com.tia102g4.event.model.Event;
import com.tia102g4.event.service.EventService;
import com.tia102g4.event.service.EventServiceImpl;
import com.tia102g4.member.model.Member;
import com.tia102g4.member.service.MemberService;
import com.tia102g4.member.service.MemberServiceImp1;
//import com.tia102g4.member.service.MemberService;
//import com.tia102g4.member.service.MemberServiceImpl;
import com.tia102g4.myorder.model.MyOrder;
//import com.tia102g4.myorder.service.MailService;
import com.tia102g4.myorder.service.MyOrderService;
import com.tia102g4.myorder.service.MyOrderServiceImpl;
import com.tia102g4.rest.model.Restaurant;
import com.tia102g4.rest.service.RestaurantService;
import com.tia102g4.rest.service.RestaurantServiceImpl;
import com.tia102g4.vote.service.VoteService;
import com.tia102g4.vote.service.VoteServiceImpl;
//import com.tia102g4.rest.service.RestService;
//import com.tia102g4.rest.service.RestServiceImpl;
//import com.tia102g4.vote.service.VoteService;
//import com.tia102g4.vote.service.VoteServiceImpl;

@WebServlet("/myorder/membermyorder.do")
public class MemberMyOrderServlet extends HttpServlet {

	    private MyOrderService orderService;
	    private EventService eventService;
	    private MemberService memberService;
	    private RestaurantService restService;
	    private VoteService voteService;
	    private BlackListService blackListService;


	    @Override
	    public void init() throws ServletException {
	        orderService = new MyOrderServiceImpl();
	        eventService = new EventServiceImpl();
	        memberService = new MemberServiceImp1();
	        restService = new RestaurantServiceImpl();
	        voteService = new VoteServiceImpl();
	        blackListService = new BlackListServiceImpl();
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
//		Long memberId = 1L;
		Long memberId = ((Number) req.getSession().getAttribute("memberId")).longValue();
		List<MyOrder> orderList = orderService.getOrderStatus1Member(memberId);
		req.getSession().setAttribute("orderList", orderList);
		return "/frontstage/memberFrontend/myorder/member_orderStatus1.jsp";
	}
	
	private String getOrderStatus2Member(HttpServletRequest req, HttpServletResponse res) {
		Long memberId = ((Number) req.getSession().getAttribute("memberId")).longValue();
		List<MyOrder> orderList = orderService.getOrderStatus2Member(memberId);
		req.getSession().setAttribute("orderList", orderList);
		return "/frontstage/memberFrontend/myorder/member_orderStatus2.jsp";
	}
	
	private String getOrderStatus3Member(HttpServletRequest req, HttpServletResponse res) {
		Long memberId = ((Number) req.getSession().getAttribute("memberId")).longValue();
		List<MyOrder> orderList = orderService.getOrderStatus3Member(memberId);
		req.getSession().setAttribute("orderList", orderList);
		return "/frontstage/memberFrontend/myorder/member_orderStatus3.jsp";
	}
	

	// =================================member 取消訂單================================================

	private String updateOrderStatus2Mem(HttpServletRequest req, HttpServletResponse res) throws InterruptedException {
		String action = req.getParameter("action");
		Long memberId = ((Number) req.getSession().getAttribute("memberId")).longValue();
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

//	 ========================================add order===============================================
	 private void sendOrderConfirmationEmail(MyOrder order) {
	        final String username = "chugetherfood@gmail.com"; 
	        final String password = "javq rros emec ruwf"; 

	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");

	        Session session = Session.getInstance(props,
	            new javax.mail.Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(username, password);
	                }
	            });

	        try {
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("Maitran.ttvh@gmail.com"));
	            message.setRecipients(Message.RecipientType.TO,
//	                InternetAddress.parse(order.getMember().getEmail()));
	            	InternetAddress.parse("Maitran.ttvh@gmail.com"));	//測試
	            message.setSubject("Chugether 揪團訂單明細");
	        
	            String htmlContent = "<html>" +
	            		  "<h2>Dear " + order.getMemberName() + ",</h2>" +
	            		  "<div style=\"border: 1px solid #ffc107; background-color: #ffc107; border-radius: 10px; width: 300px; height: 450px; margin-left: 20%;\">" +
	            		    "    <div style=\"text-align: center; font-size: 20px; padding-top: 15px;\">恭喜揪團成功~<br>這是您的訂單明細</div>" +
	            		    "    <table class=\"receipt_detail\" style=\"border:1px solid white; background-color: white; text-align: left; border-radius: 10px; width: 300px; height: 280px; margin-left: 0px;margin-top: 10px;\">" +
	            		    "        <tr><th>訂單編號:</th><td>" + order.getOrderId() + "</td></tr>" +
	            		    "        <tr><th>餐廳:</th><td>" + order.getRestaurant().getRestName() + "</td></tr>" +
	            		    "        <tr><th>用餐日期:</th><td>" + order.getReserDate() + "</td></tr>" +
	            		    "        <tr><th>用餐時間:</th><td>" + order.getReserTime() + "</td></tr>" +
	            		    "        <tr><th>人數:</th><td>" + order.getReserPeopleNumber() + "</td></tr>" +
	            		    "        <tr><th>備註:</th><td>" + order.getReserNote() + "</td></tr>" +
	            		    "    </table>" +
	            		    "    <div style=\"display: flex; flex-wrap: nowrap;\">" +
	            		    "        <div style=\"font-size: 20px; padding-bottom: 10px; padding-top:10px; padding-left: 85px; text-align: center;\">Chugether <br>揪團開趴吧~</div>" +
	            		    "    </div>" +
	            		    "</div>"+
	                "</body>" +
	                "</html>";
	            
	          
	            message.setContent(htmlContent, "text/html; charset=utf-8");
	            Transport.send(message);


	        } catch (MessagingException e) {
	            throw new RuntimeException(e);
	        }
	    }
	 
	 //這個方法是用假資料
	 private String addOrder(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		    MyOrder order = new MyOrder();
		    Restaurant restaurant = new Restaurant();
		    restaurant.setRestId(1L); 
		    restaurant.setRestName("金龍軒");
		    
		    Event event = new Event();
		    event.setEventId(19L); 
		    
		    Member member = new Member();
		    member.setMemberId(1L); 
		    
		    order.setMember(member);
		    order.setEvent(event);
		    order.setRestaurant(restaurant);
		    order.setMemberName("王小明");
		    order.setPhone("0912345678");
		    order.setOrderDate(new Timestamp(System.currentTimeMillis()));
		    order.setOrderStatus("1");
		    order.setReserDate(Date.valueOf("2024-08-25"));
		    order.setReserTime(Time.valueOf("18:00:00"));
		    order.setReserPeopleNumber(12);
		    order.setReserNote("無");

		    try {
		        boolean isBlacklisted = blackListService.isMemberInBlackList(member.getMemberId(), restaurant.getRestId());
		        
		        if (isBlacklisted) {
		            req.setAttribute("errorMsg", "餐廳拒絕訂單，請往其它餐廳訂單。");
		          
		            return "/frontstage/memberFrontend/myorder/member_order_index.jsp";
		        }
		        orderService.addOrder(order);
		       
		        sendOrderConfirmationEmail(order);
		      
		    } catch (Exception e) {
		        e.printStackTrace();
		        req.setAttribute("errorMsg", "訂單處理失敗，請稍後再試。");
		        return "/error.jsp";
		    }

		    req.getSession().setAttribute("order", order);
		    return getOrderStatus1Member(req, res);
		}


	 
//	 private String addOrder(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//
//		  
////		    Member member = (Member) req.getSession().getAttribute("loggedInMember");
//		 	Long memberId = 1L;
//		 	Member member = new Member();
//		    member.setMemberId(1L); 
//		 
//		   
////		    List<Event> eventList = (List<Event>) req.getAttribute("eventList");
////		    Event event = eventList.get(0);
//		    Event event = new Event();
//		    event.setEventId(9L);
//
//		    Long winningRestId = (Long) req.getAttribute("winningRestId");
//
//		    Restaurant restaurant = new Restaurant();
//		    restaurant.setRestId(winningRestId);
//		   
//
//	
//		    MyOrder order = new MyOrder();
//		    order.setMember(member);
//		    order.setEvent(event);
//		    order.setRestaurant(restaurant);
//		    order.setMemberName(member.getName());
//		    order.setPhone(member.getMobileNo()); 
//		    order.setOrderDate(new Timestamp(System.currentTimeMillis())); 
//		    order.setOrderStatus("1"); 
//		    order.setReserDate(event.getDate()); 
//		    order.setReserTime(event.getTime()); 
//		    order.setReserPeopleNumber(3); 
//		    order.setReserNote(event.getInfo()); 
//
//		    try {
//		       
//		        boolean isBlacklisted = blackListService.isMemberInBlackList(member.getMemberId(), restaurant.getRestId());
//
//		        if (isBlacklisted) {
//		        	req.getSession().setAttribute("errorMsg", "餐廳拒絕訂單，請往其它餐廳訂單。");
//		            return "/frontstage/memberFrontend/myorder/member_order_index.jsp";
//		        }
//
//		       
//		        orderService.addOrder(order);
//
//		      
//		        sendOrderConfirmationEmail(order);
//
//		    } catch (Exception e) {
//		        e.printStackTrace();
//		        req.getSession().setAttribute("errorMsg", "訂單處理失敗，請稍後再試。");
//		        return "/error.jsp";
//		    }
//		    req.getSession().setAttribute("order", order);
//		    return getOrderStatus1Member(req, res);
//		}

//===================================================================================================================

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
