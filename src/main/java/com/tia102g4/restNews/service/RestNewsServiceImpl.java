package com.tia102g4.restNews.service;

import static com.tia102g4.util.Constants.PAGE_MAX_RESULT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tia102g4.restNews.dao.RestNewsDAO;
import com.tia102g4.restNews.dao.RestNewsDAOImpl;
import com.tia102g4.restNews.mapper.RestNewsMapper;
import com.tia102g4.restNews.model.RestaurantNews;
import com.tia102g4.restNews.to.req.RestNewsDeleteReqTO;
import com.tia102g4.restNews.to.req.RestNewsReqTO;
import com.tia102g4.restNews.to.req.RestNewsUpdateReqTO;

public class RestNewsServiceImpl implements RestNewsService {

	private RestNewsDAO dao;
	private RestNewsMapper restNewsMapper = new RestNewsMapper();

	public RestNewsServiceImpl() {
		dao = new RestNewsDAOImpl();
	}

	@Override
	public void create(RestNewsReqTO reqTO) {
		RestaurantNews restNews = restNewsMapper.setRestNews(reqTO);
		Long restId = reqTO.getRestId();
		dao.insert(restNews, restId);
	}

	@Override
	public void update(RestNewsReqTO reqTO) {
		RestaurantNews restNews = restNewsMapper.setRestNews(reqTO);
		Long restId = reqTO.getRestId();
		dao.update(restNews, restId);
	}

	@Override
	public void delete(RestNewsDeleteReqTO reqTO) {
		RestaurantNews restNews = new RestaurantNews();
		restNews.setDeleted(reqTO.getDeleted());
		restNews.setNewsId(reqTO.getNewsId());
		dao.delete(restNews);
	}

	@Override
	public List<RestNewsReqTO> getAllRestNews(int currentPage) {
		List<RestaurantNews> restNews = dao.getAll(currentPage);
		List<RestNewsReqTO> reqTOs = new ArrayList<>();
		for (RestaurantNews restNew : restNews) {
			RestNewsReqTO dto = restNewsMapper.setRestNewsReqTO(restNew);
			reqTOs.add(dto);
		}
		return reqTOs;
	}

	@Override
	public List<RestNewsReqTO> getRestNewsByCompositeQuery(Map<String, String[]> map) {
		Map<String, String> query = new HashMap<>();
		// Map.Entry即代表一組key-value
		Set<Map.Entry<String, String[]>> entry = map.entrySet();

		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			// 因為請求參數裡包含了action，做個去除動作
			if ("action".equals(key)) {
				continue;
			}
			// 若是value為空即代表沒有查詢條件，做個去除動作
			String value = row.getValue()[0];
			if (value.isEmpty() || value == null) {
				continue;
			}
			query.put(key, value);
		}
		List<RestaurantNews> restNews = dao.getByCompositeQuery(query);
		List<RestNewsReqTO> reqTOs = new ArrayList<>();
		for (RestaurantNews restNew : restNews) {
			RestNewsReqTO dto = restNewsMapper.setRestNewsReqTO(restNew);
			reqTOs.add(dto);
		}
		System.out.println(query);

		return reqTOs;
	}

	@Override
	public int getPageTotal() {
		long total = dao.getTotal();
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}

}
