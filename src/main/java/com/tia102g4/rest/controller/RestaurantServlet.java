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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.tia102g4.rest.model.Restaurant;
import com.tia102g4.rest.service.RestaurantService;
import com.tia102g4.rest.service.RestaurantServiceImpl;
import com.tia102g4.rest.to.RestaurantReqTO;
import com.tia102g4.rest.to.RestaurantResetPasswordReqTO;
import com.tia102g4.rest.to.RestaurantUpdateReqTO;
import com.tia102g4.util.BaseResponse;

@WebServlet("/rest/rest.do")
public class RestaurantServlet extends HttpServlet {
	private RestaurantService restService;
	private BaseResponse baseResponse = new BaseResponse();
	private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

	@Override
	public void init() throws ServletException {
		restService = new RestaurantServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		HttpSession session = req.getSession();
		Long restId = (Long) session.getAttribute("restId");

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
				restaurantLogin(req, requestBody);
				res.setStatus(HttpServletResponse.SC_OK);
				break;
			case "add":
				add(requestBody);
				res.setStatus(HttpServletResponse.SC_OK);
				break;
			case "restaurantLogout":
				restaueantLogout(req, res);
				break;
			case "findIdByUser":
				jsonObject = findIdByUser(requestBody, restId);
				break;
			case "updateForRest":
				updateForRest(requestBody, restId);
				break;
			case "resetPassword":
				resetPassword(requestBody);
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
		} catch (Exception e) {
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
		return baseResponse.gsonBuilderForJsonResponse(List, currentPage, totalPageQty);
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
		RestaurantReqTO reqTO = gson.fromJson(requestBody, RestaurantReqTO.class);
		restService.create(reqTO);
	}

	// 後台更新資料
	private void update(String requestBody) {
		Restaurant rest = gson.fromJson(requestBody, Restaurant.class);
		restService.update(rest);
	}

	// 前台修改資料
	private void updateForRest(String requestBody, Long restId) {
		System.out.println(requestBody);
		RestaurantReqTO reqTO = gson.fromJson(requestBody, RestaurantReqTO.class);
		restService.updateForRest(reqTO, restId);
	}

	private JsonObject findIdByUser(String requestBody, Long restId) {
		RestaurantReqTO resultTO = restService.findIdByUser(restId);
		return gson.toJsonTree(resultTO).getAsJsonObject();
	}

	private void delete(String requestBody) {
		Restaurant rest = gson.fromJson(requestBody, Restaurant.class);
		restService.delete(rest);
	}

	private void restaurantLogin(HttpServletRequest req, String requestBody) throws IOException, LoginException {
		Restaurant rest = gson.fromJson(requestBody, Restaurant.class);
		Restaurant restId = restService.findAccountByUser(rest);
		HttpSession session = req.getSession();
		session.setAttribute("restId", restId.getRestId());
	}

	private void restaueantLogout(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		res.setStatus(HttpServletResponse.SC_OK);
	}
	
	private void resetPassword(String requestBody) {
		RestaurantResetPasswordReqTO reqTO = gson.fromJson(requestBody, RestaurantResetPasswordReqTO.class);
		restService.resetPassword(reqTO);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
