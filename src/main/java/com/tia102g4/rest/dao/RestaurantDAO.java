package com.tia102g4.rest.dao;

import java.util.List;
import java.util.Map;

import com.tia102g4.rest.model.Restaurant;

public interface RestaurantDAO {
	
	long insert(Restaurant entity);
	
	long update(Restaurant entty);
	
	long delete(Long id);
	
	Restaurant getById(Long id);
	
	List<Restaurant> getAll();
	
	List<Restaurant> getByCompositeQuery(Map<String, String> map);
	
	List<Restaurant> getAll(int currentPage);
	
	long getTotal();
	
}
