package com.tia102g4.restNews.dao;

import java.util.List;
import java.util.Map;

import com.tia102g4.restNews.model.RestaurantNews;

public interface RestNewsDAO {

	void insert(RestaurantNews entity, Long restId);

	Long update(RestaurantNews entity, Long restId);

	void delete(RestaurantNews entity);

	boolean isOverlappingPeriods(RestaurantNews restaurantNews, Long restId);

	List<RestaurantNews> getByCompositeQuery(Map<String, String> map, Long restId);

	List<RestaurantNews> getAll(int currentPage, Long restId);

	List<RestaurantNews> getAll();

	long getTotal();
}
