package com.tia102g4.restNews.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tia102g4.restNews.service.RestNewsService;
import com.tia102g4.restNews.service.RestNewsServiceImpl;
import com.tia102g4.restNews.to.req.RestNewsDeleteReqTO;
import com.tia102g4.restNews.to.req.RestNewsReqTO;
import com.tia102g4.util.BaseResponse;

@WebServlet("/restNews/restNews.do")
public class RestNewsServlet extends HttpServlet {
	private RestNewsService restNewsService;
	private BaseResponse baseResponse = new BaseResponse();
	private Gson gson = new Gson();

	@Override
	public void init() throws ServletException {
		restNewsService = new RestNewsServiceImpl();
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
		case "getAllForMember":
			jsonObject = getAllForMember(req);
			break;
		case "compositeQuery":
			jsonObject = getCompositeQuery(req, res);
			break;
		case "add":
			add(requestBody);
			res.setStatus(HttpServletResponse.SC_OK);
			break;
		case "update":
			update(requestBody);
			res.setStatus(HttpServletResponse.SC_OK);
			break;
		case "delete":
			delete(requestBody);
			break;
		}
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(gson.toJson(jsonObject));
		}catch (ValidationException e) {
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
		int totalPageQty = restNewsService.getPageTotal();
		List<RestNewsReqTO> reqTOList = restNewsService.getAllRestNews(currentPage);

		if (req.getSession().getAttribute("RestNewsPageQty") == null) {
			req.getSession().setAttribute("RestNewsPageQty", totalPageQty);
		}
		return baseResponse.jsonResponse(reqTOList, currentPage, totalPageQty);
	}
	// 查詢所有資料給會員前台
	private JsonObject getAllForMember(HttpServletRequest req) {
		List<RestNewsReqTO> reqTOList = restNewsService.getAllRestNews();
		return baseResponse.jsonResponse(reqTOList);
	}

	// 複合查詢
	private JsonObject getCompositeQuery(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String[]> map = req.getParameterMap();

		// 如果map沒資料就回傳空值
		if (map == null) {
			return null;
		}
		List<RestNewsReqTO> reqTOList = restNewsService.getRestNewsByCompositeQuery(map);
		return baseResponse.jsonResponse(reqTOList);
	}

	// 新增資料
	private void add(String requestBody) {
		RestNewsReqTO reqTO = gson.fromJson(requestBody, RestNewsReqTO.class);
		restNewsService.create(reqTO);
	}

	// 更新資料
	private void update(String requestBody) {
		RestNewsReqTO reqTO = gson.fromJson(requestBody, RestNewsReqTO.class);
		restNewsService.update(reqTO);
	}

	// 刪除資料
	private void delete(String requestBody) {
		RestNewsDeleteReqTO reqTO = gson.fromJson(requestBody, RestNewsDeleteReqTO.class);
		restNewsService.delete(reqTO);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
