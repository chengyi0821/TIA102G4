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

    private JsonObject getAllAnnos(HttpServletRequest req) {
        String page = req.getParameter("page");
        int currentPage = (page == null) ? 1 : Integer.parseInt(page);
        int totalPageQty = annoService.getPageTotal();
        List<Anno> annoList = annoService.getAllAnnos(currentPage);

        if (req.getSession().getAttribute("annoPageQty") == null) {
            req.getSession().setAttribute("annoPageQty", totalPageQty);
        }
        return baseResponse.jsonResponse(annoList, currentPage, totalPageQty);
    }

    private JsonObject getCompositeAnnosQuery(HttpServletRequest req, HttpServletResponse res) {
        Map<String, String[]> map = req.getParameterMap();
      
        if (map != null) {
            List<Anno> annoList = annoService.getAnnosByCompositeQuery(map);
            
            int currentPage = Integer.parseInt(req.getParameter("currentPage") != null ? req.getParameter("currentPage") : "1");
            int totalPageQty = annoService.getPageTotal();  
            
            return baseResponse.jsonResponse(annoList, currentPage, totalPageQty);
        }
        return null;
    }
    
    private void add(String requestBody) {
        AnnoReqTO reqTO = gson.fromJson(requestBody, AnnoReqTO.class);
    	annoService.create(reqTO);
    }
    
    private void update(String requestBody) {
        AnnoUpdateReqTO reqTO = gson.fromJson(requestBody, AnnoUpdateReqTO.class);
        System.out.println(reqTO);
    	annoService.update(reqTO);
    }
    
    private void delete(String requestBody) {
    	AnnoDeleteReqTO reqTO = gson.fromJson(requestBody, AnnoDeleteReqTO.class);
    	annoService.delete(reqTO);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }
}
