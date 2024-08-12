package com.tia102g4.cs.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.tia102g4.cs.service.CSService;
import com.tia102g4.cs.service.MemberFeedbackServiceImpl;
import com.tia102g4.cs.to.req.CSReqTO;
import com.tia102g4.util.BaseResponse;

@WebServlet("/cs/memberFeedback.do")
public class MemberFeedbackServlet extends HttpServlet {
	private CSService memberFeedbackService;
	private BaseResponse baseResponse = new BaseResponse();
	private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

	@Override
	public void init() throws ServletException {
		memberFeedbackService = new MemberFeedbackServiceImpl();
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

		switch (action) {
		case "getAll":
			jsonObject = getAll(req);
			break;
		case "compositeQuery":
			jsonObject = getCompositeCSQuery(req, res);
			break;
		case "add":
			insert(requestBody);
			break;
		case "deleted":
			delete(requestBody);
			break;
		}
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(gson.toJson(jsonObject));
	}

	// 查詢所有資料
	private JsonObject getAll(HttpServletRequest req) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		int totalPageQty = memberFeedbackService.getPageTotal();
		List<CSReqTO> reqTOList = memberFeedbackService.getAllCS(currentPage);

		if (req.getSession().getAttribute("csMemberPageQty") == null) {
			req.getSession().setAttribute("csMemberPageQty", totalPageQty);
		}
		return baseResponse.gsonBuilderForJsonResponse(reqTOList, currentPage, totalPageQty);
	}

	// 複合查詢
	private JsonObject getCompositeCSQuery(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String[]> map = req.getParameterMap();

		// 如果map沒資料就回傳空值
		if (map == null) {
			return null;
		}
		List<CSReqTO> reqTOList = memberFeedbackService.getCSByCompositeQuery(map);

		return baseResponse.gsonBuilderForJsonResponse(reqTOList);
	}

	// 填寫餐廳意見表
	private void insert(String requestBody) {
		CSReqTO reqTO = gson.fromJson(requestBody, CSReqTO.class);
		memberFeedbackService.insert(reqTO);
	}

	// 刪除單筆信件
	private void delete(String requestBody) {
		CSReqTO reqTO = gson.fromJson(requestBody, CSReqTO.class);
		memberFeedbackService.delete(reqTO);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}