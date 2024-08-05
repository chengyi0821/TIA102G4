package com.tia102g4.util;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

//用於json傳輸使用
public class BaseResponse {
	private Gson gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	private Gson gson = new Gson();
	private JsonObject jsonResponse = new JsonObject();

	public JsonObject jsonResponse(List<?> List, int currentPage, int totalPageQty) {

		jsonResponse.add("List", gson.toJsonTree(List));
		jsonResponse.addProperty("currentPage", currentPage);
		jsonResponse.addProperty("totalPageQty", totalPageQty);

		return jsonResponse;
	}

	public JsonObject jsonResponse(List<?> List) {

		jsonResponse.add("List", gson.toJsonTree(List));
		return jsonResponse;
	}

	public JsonObject gsonBuilderForJsonResponse(List<?> List, int currentPage, int totalPageQty) {

		jsonResponse.add("List", gsonBuilder.toJsonTree(List));
		jsonResponse.addProperty("currentPage", currentPage);
		jsonResponse.addProperty("totalPageQty", totalPageQty);

		return jsonResponse;
	}

	public JsonObject gsonBuilderForJsonResponse(List<?> List) {
		jsonResponse.add("List", gsonBuilder.toJsonTree(List));
		return jsonResponse;
	}
}
