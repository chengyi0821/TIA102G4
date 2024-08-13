package com.tia102g4.restNews.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.tia102g4.restNews.model.RestaurantNews;

public interface RestNewsDAO {
	
	void insert(RestaurantNews entity, Long restId);
	
	Long update(RestaurantNews entity, Long restId);
	
	void delete(RestaurantNews entity);
	
	public List<RestaurantNews> getOverlappingNews(Long restId, Date startDate, Date endDate);
	
	List<RestaurantNews> getByCompositeQuery(Map<String, String> map);
	
	List<RestaurantNews> getAll(int currentPage);
	
	List<RestaurantNews> getAll();
	
	long getTotal();
}
