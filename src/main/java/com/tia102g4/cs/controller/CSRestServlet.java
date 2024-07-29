package com.tia102g4.cs.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.tia102g4.cs.model.CustomerService;
import com.tia102g4.cs.service.CSRestServiceImlp;
import com.tia102g4.cs.service.CSService;
import com.tia102g4.util.BaseResponse;

@WebServlet("/cs/cs.do")
public class CSRestServlet extends HttpServlet {

	private CSService customerServiceRest;
	private BaseResponse baseResponse = new BaseResponse();
	private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

	@Override
	public void init() throws ServletException {
		customerServiceRest = new CSRestServiceImlp();
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
			jsonObject = getAllCS(req);
			break;
		case "compositeQuery":
			break;
		case "add":
			break;
		}
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(gson.toJson(jsonObject));
	}

	private JsonObject getAllCS(HttpServletRequest req) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		int totalPageQty = customerServiceRest.getPageTotal();
		List<CustomerService> csList = customerServiceRest.getAllCS(currentPage);

		if (req.getSession().getAttribute("annoPageQty") == null) {
			req.getSession().setAttribute("annoPageQty", totalPageQty);
		}
		return baseResponse.gsonBuilderForJsonResponse(csList, currentPage, totalPageQty);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
