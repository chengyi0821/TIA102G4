package com.tia102g4.restNews.service;

import java.util.List;
import java.util.Map;

import com.tia102g4.restNews.to.req.RestNewsDeleteReqTO;
import com.tia102g4.restNews.to.req.RestNewsReqTO;
import com.tia102g4.restNews.to.req.RestNewsUpdateReqTO;

public interface RestNewsService {
	void create(RestNewsReqTO reqTO);

	void update(RestNewsReqTO reqTO);

	void delete(RestNewsDeleteReqTO reqTO);

	List<RestNewsReqTO> getAllRestNews(int currentPage);
	
	List<RestNewsReqTO> getAllRestNews();

	int getPageTotal();

	List<RestNewsReqTO> getRestNewsByCompositeQuery(Map<String, String[]> map);
}
