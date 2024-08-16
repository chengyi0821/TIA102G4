package com.tia102g4.restNews.service;

import static com.tia102g4.util.Constants.PAGE_MAX_RESULT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.tia102g4.restNews.dao.RestNewsDAO;
import com.tia102g4.restNews.dao.RestNewsDAOImpl;
import com.tia102g4.restNews.mapper.RestNewsMapper;
import com.tia102g4.restNews.model.RestaurantNews;
import com.tia102g4.restNews.to.req.RestNewsDeleteReqTO;
import com.tia102g4.restNews.to.req.RestNewsReqTO;

public class RestNewsServiceImpl implements RestNewsService {

	private RestNewsDAO dao;
	private RestNewsMapper restNewsMapper = new RestNewsMapper();
	private Validator validator;

	public RestNewsServiceImpl() {
		dao = new RestNewsDAOImpl();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Override
	public void create(RestNewsReqTO reqTO) {
		RestaurantNews restNews = restNewsMapper.setRestNews(reqTO);
		Long restId = reqTO.getRestId();
		validateRestNews(restNews); // 驗證
		if (dao.isOverlappingPeriods(restNews, restId)) {
			throw new IllegalStateException("該餐廳日期區間已有公告，無法新增公告。");
		}
		dao.insert(restNews, restId);
	}

	@Override
	public void update(RestNewsReqTO reqTO) {
		RestaurantNews restNews = restNewsMapper.setRestNews(reqTO);
		Long restId = reqTO.getRestId();
		validateRestNews(restNews); // 驗證
		if (dao.isOverlappingPeriods(restNews, restId)) {
			throw new IllegalStateException("該餐廳日期區間已有公告，無法更新公告。");
		}
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
	public List<RestNewsReqTO> getAllRestNews(int currentPage, Long restId) {
		List<RestaurantNews> restNews = dao.getAll(currentPage, restId);
		List<RestNewsReqTO> reqTOs = new ArrayList<>();
		for (RestaurantNews restNew : restNews) {
			RestNewsReqTO dto = restNewsMapper.setRestNewsReqTO(restNew);
			reqTOs.add(dto);
		}
		return reqTOs;
	}

	@Override
	public List<RestNewsReqTO> getAllRestNews() {
		List<RestaurantNews> restNews = dao.getAll();
		List<RestNewsReqTO> reqTOs = new ArrayList<>();
		for (RestaurantNews restNew : restNews) {
			RestNewsReqTO dto = restNewsMapper.setRestNewsReqTO(restNew);
			reqTOs.add(dto);
		}
		return reqTOs;
	}

	@Override
	public List<RestNewsReqTO> getRestNewsByCompositeQuery(Map<String, String[]> map, Long restId) {
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
		List<RestaurantNews> restNews = dao.getByCompositeQuery(query, restId);
		List<RestNewsReqTO> reqTOs = new ArrayList<>();
		for (RestaurantNews restNew : restNews) {
			RestNewsReqTO dto = restNewsMapper.setRestNewsReqTO(restNew);
			reqTOs.add(dto);
		}
		return reqTOs;
	}

	@Override
	public int getPageTotal() {
		long total = dao.getTotal();
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}

	private void validateRestNews(RestaurantNews restNews) {
		Set<ConstraintViolation<RestaurantNews>> violations = validator.validate(restNews);
		if (!violations.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (ConstraintViolation<RestaurantNews> violation : violations) {
				sb.append(violation.getMessage());
			}
			throw new ConstraintViolationException(violations);
		}
	}

}
