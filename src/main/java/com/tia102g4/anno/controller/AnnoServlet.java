package com.tia102g4.anno.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tia102g4.anno.model.Anno;
import com.tia102g4.anno.service.AnnoService;
import com.tia102g4.anno.service.AnnoServiceImpl;
import com.tia102g4.anno.to.req.AnnoDeleteReqTO;
import com.tia102g4.anno.to.req.AnnoReqTO;
import com.tia102g4.anno.to.req.AnnoUpdateReqTO;
import com.tia102g4.util.BaseResponse;

@WebServlet("/anno/anno.do")
public class AnnoServlet extends HttpServlet {

	private AnnoService annoService;
	private BaseResponse baseResponse = new BaseResponse();
	private Gson gson = new Gson();

	@Override
	public void init() throws ServletException {
		annoService = new AnnoServiceImpl();
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
			jsonObject = getAllAnnos(req);
			break;
		case "compositeQuery":
			jsonObject = getCompositeAnnosQuery(req, res);
			break;
		case "add":
			add(requestBody);
			break;
		case "update":
			update(requestBody);
			break;
		case "delete":
			delete(requestBody);
			break;
		}
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(gson.toJson(jsonObject));
	}

	// 查詢所有資料
	private JsonObject getAllAnnos(HttpServletRequest req) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		int totalPageQty = annoService.getPageTotal();
		List<AnnoReqTO> reqTOList = annoService.getAllAnnos(currentPage);

		if (req.getSession().getAttribute("annoPageQty") == null) {
			req.getSession().setAttribute("annoPageQty", totalPageQty);
		}
		return baseResponse.jsonResponse(reqTOList, currentPage, totalPageQty);
	}

	// 複合查詢
	private JsonObject getCompositeAnnosQuery(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String[]> map = req.getParameterMap();

		// 如果map沒資料就回傳空值
		if (map == null) {
			return null;
		}
		List<AnnoReqTO> reqTOList = annoService.getAnnosByCompositeQuery(map);
		return baseResponse.jsonResponse(reqTOList);
	}

	// 新增資料
	private void add(String requestBody) {
		AnnoReqTO reqTO = gson.fromJson(requestBody, AnnoReqTO.class);
		annoService.create(reqTO);
	}

	// 更新資料
	private void update(String requestBody) {
		AnnoUpdateReqTO reqTO = gson.fromJson(requestBody, AnnoUpdateReqTO.class);
		annoService.update(reqTO);
	}

	// 刪除資料
	private void delete(String requestBody) {
		AnnoDeleteReqTO reqTO = gson.fromJson(requestBody, AnnoDeleteReqTO.class);
		annoService.delete(reqTO);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
