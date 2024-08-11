package com.tia102g4.favorite.controller;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tia102g4.favorite.model.Favorite;
import com.tia102g4.favorite.service.FavoriteService;
import com.tia102g4.favorite.service.FavoriteServiceImpl;
import com.tia102g4.member.model.Member;
import com.tia102g4.rest.model.Restaurant;

@WebServlet("/favorite/favorite.do")
public class FavoriteServlet extends HttpServlet {
	private FavoriteService favoriteService;

	@Override
	public void init() throws ServletException {
		favoriteService = new FavoriteServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = null;

		try {
			switch (action) {
				case "getFav":
					forwardPath = getFav(req, res);
					break;
				case "addFav":
					forwardPath = addFav(req, res);
					break;
				case "deleteFav":
					forwardPath = deleteFav(req, res);
					break;
				default:
					forwardPath = "/error.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "An error occurred: " + e.getMessage());
			forwardPath = "/error.jsp";
		}
		if (forwardPath != null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		} else {
			res.sendRedirect(req.getContextPath() + "/error.jsp");
		}
	}
	
	private String getFav(HttpServletRequest req, HttpServletResponse res) {
		Long memberId = 1L; //測試用
		
//		HttpSession session = req.getSession();
//	    Long memberId = (Long) session.getAttribute("memberId");
		
		List<Favorite> favorites = favoriteService.getFav(memberId);
		req.setAttribute("favorites", favorites);
		return "/frontstage/memberFrontend/favorite/favoriteList.jsp";
	}

	private String addFav(HttpServletRequest req, HttpServletResponse res) {
		  Long memberId = 1L;  // 測試用
		  
//		  HttpSession session = req.getSession();
//		  Long memberId = (Long) session.getAttribute("memberId");
		    
		    
		    String restIdParam = req.getParameter("restId");

		    if (restIdParam != null && !restIdParam.isEmpty()) {
		        try {
		            Long restId = Long.parseLong(restIdParam);

		            Member member = new Member();
		            member.setMemberId(memberId);

		            Restaurant restaurant = new Restaurant();
		            restaurant.setRestId(restId);

		            Favorite favorite = new Favorite();
		            favorite.setMember(member);
		            favorite.setRestaurant(restaurant);
		            favorite.setAddTime(new Timestamp(System.currentTimeMillis()));

		            if (favoriteService.addFav(favorite)) {
		                req.setAttribute("successMessage", "收藏餐廳成功！");
		                return "/myorder/membermyorder.do?action=getOrderStatus3Mem";
		            } else {
		                req.setAttribute("errorMessage", "這餐廳已經在收藏列表");
		                return "/myorder/membermyorder.do?action=getOrderStatus3Mem";
		            }
		        } catch (NumberFormatException e) {  
		            req.setAttribute("errorMessage", "error");
		            e.printStackTrace();  
		            return "/error.jsp";
		        } catch (Exception e) {
		            req.setAttribute("errorMessage", "error");
		            e.printStackTrace();  
		            return "/error.jsp";
		        }
		    } else {
		        req.setAttribute("errorMessage", "error");
		        return "/error.jsp";
		    }
	}


	private String deleteFav(HttpServletRequest req, HttpServletResponse res) throws InterruptedException, IOException {
	    Long memberId = 1L; 
	    
//	    HttpSession session = req.getSession();
//		Long memberId = (Long) session.getAttribute("memberId");
	    
	    String restIdParam = req.getParameter("restId");

	    if (restIdParam != null && !restIdParam.isEmpty()) {
	        try {
	            Long restId = Long.parseLong(restIdParam);
	            favoriteService.deleteFav(memberId, restId);
	            Thread.sleep(1500);
	            return getFav(req, res);
	        } catch (NumberFormatException e) {
	            req.setAttribute("errorMsg", "Invalid restaurant ID.");
	            return "/error.jsp";
	        }
	    } else {
	        req.setAttribute("errorMsg", "Restaurant ID is required.");
	        return "/error.jsp";
	    }
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
