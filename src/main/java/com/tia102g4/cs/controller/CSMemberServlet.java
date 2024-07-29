package com.tia102g4.cs.controller;

import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;
import com.tia102g4.cs.service.CSService;
import com.tia102g4.util.BaseResponse;

public class CSMemberServlet extends HttpServlet{
	
	private CSService csMemberCsService;
	private BaseResponse baseResponse = new BaseResponse();
	private Gson gson = new Gson();
	
}
