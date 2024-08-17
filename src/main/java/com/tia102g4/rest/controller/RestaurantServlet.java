package com.tia102g4.rest.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tia102g4.rest.model.Restaurant;
import com.tia102g4.rest.service.RestaurantService;
import com.tia102g4.rest.service.RestaurantServiceImpl;
import com.tia102g4.rest.to.RestaurantReqTO;
import com.tia102g4.util.BaseResponse;

@WebServlet("/rest/rest.do")
public class RestaurantServlet extends HttpServlet {
	private RestaurantService restService;
	private BaseResponse baseResponse = new BaseResponse();
	private Gson gson = new Gson();

	@Override
	public void init() throws ServletException {
		restService = new RestaurantServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		BufferedReader reader = req.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
		}
		String requestBody = stringBuilder.toString();
		JsonObject jsonObject = null;
		try {
			switch (action) {
			case "getAll":
				jsonObject = getAll(req);
				break;
			case "compositeQuery":
				jsonObject = getCompositeQuery(req);
				break;
			case "update":
				update(requestBody);
				break;
			case "delete":
				delete(requestBody);
				break;
			case "restaurantLogin":
				RestaurantLogin(req, requestBody);
				res.setStatus(HttpServletResponse.SC_OK);
				break;
			case "add":
				add(requestBody);
				res.setStatus(HttpServletResponse.SC_OK);
				break;
			case "restaurantLogout":
				RestaueantLogout(req, res);
				break;
			}
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(gson.toJson(jsonObject));
		} catch (LoginException e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(e.getMessage());
			out.flush();
		}
	}

	// 查詢所有資料
	private JsonObject getAll(HttpServletRequest req) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		int totalPageQty = restService.getPageTotal();
		List<Restaurant> List = restService.getAll(currentPage);

		if (req.getSession().getAttribute("annoPageQty") == null) {
			req.getSession().setAttribute("annoPageQty", totalPageQty);
		}
		return baseResponse.jsonResponse(List, currentPage, totalPageQty);
	}

	private JsonObject getCompositeQuery(HttpServletRequest req) {
		Map<String, String[]> map = req.getParameterMap();

		// 如果map沒資料就回傳空值
		if (map == null) {
			return null;
		}
		List<Restaurant> restList = restService.getByCompositeQuery(map);
		return baseResponse.jsonResponse(restList);
	}

	private void add(String requestBody) {
		System.out.println(requestBody);
		RestaurantReqTO reqTO = gson.fromJson(requestBody, RestaurantReqTO.class);
		restService.create(reqTO);
	}

	// 更新資料
	private void update(String requestBody) {
		Restaurant rest = gson.fromJson(requestBody, Restaurant.class);
		restService.update(rest);
	}

	private void delete(String requestBody) {
		Restaurant rest = gson.fromJson(requestBody, Restaurant.class);
		restService.delete(rest);
	}

	private void RestaurantLogin(HttpServletRequest req, String requestBody) throws IOException, LoginException {
		Restaurant rest = gson.fromJson(requestBody, Restaurant.class);
		Restaurant restId = restService.findAccountByUser(rest);
		HttpSession session = req.getSession();
		session.setAttribute("restId", restId.getRestId());
	}

	private void RestaueantLogout(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		res.setStatus(HttpServletResponse.SC_OK);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
