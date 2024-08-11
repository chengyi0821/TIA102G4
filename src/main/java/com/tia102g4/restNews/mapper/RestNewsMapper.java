package com.tia102g4.restNews.mapper;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.tia102g4.restNews.model.RestaurantNews;
import com.tia102g4.restNews.to.req.RestNewsReqTO;
import com.tia102g4.util.Base64Util;

import common.RestNewsType;

public class RestNewsMapper {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private Base64Util base64Util = new Base64Util();

	public RestaurantNews setRestNews(RestNewsReqTO reqTO) {
		RestaurantNews restNews = new RestaurantNews();
		try {
			// 將字串轉換為java.util.Date對象
			java.util.Date startDateUtil = dateFormat.parse(reqTO.getStartDate());
			java.util.Date endDateUtil = dateFormat.parse(reqTO.getEndDate());
			// 將java.util.Date轉換為java.sql.Date
			Date startDateSql = new Date(startDateUtil.getTime());
			Date endDateSql = new Date(endDateUtil.getTime());
			restNews.setStartDate(startDateSql);
			restNews.setEndDate(endDateSql);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		restNews.setNewsId(reqTO.getNewsId());
		restNews.setHeading(reqTO.getHeading());
		restNews.setContent(reqTO.getContent());
		restNews.setType(reqTO.getType().getTypeId());
		restNews.setImage(base64Util.base64ToByteArray(reqTO.getImage()));
		restNews.setDeleted(reqTO.getDeleted());
		return restNews;
	}

	public RestNewsReqTO setRestNewsReqTO(RestaurantNews restNews) {

		RestNewsReqTO reqTO = new RestNewsReqTO();
		reqTO.setNewsId(restNews.getNewsId());
		reqTO.setStartDate(dateFormat.format(restNews.getStartDate()));
		reqTO.setEndDate(dateFormat.format(restNews.getEndDate()));
		reqTO.setHeading(restNews.getHeading());
		reqTO.setContent(restNews.getContent());
		reqTO.setType(RestNewsType.fromTypeById(restNews.getType()));
		reqTO.setImage(base64Util.byteArrayTobase64(restNews.getImage()));
		reqTO.setDeleted(restNews.getDeleted());
		reqTO.setRestId(restNews.getRestaurant().getRestId());
		reqTO.setRestName(restNews.getRestaurant().getRestName());
		return reqTO;
	}
}
