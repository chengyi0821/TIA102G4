package com.tia102g4.rest.service;

import java.util.List;
import java.util.Map;

import com.tia102g4.rest.model.Restaurant;

public interface RestaurantService {
	void create(Restaurant entity);

	void update(Restaurant entity);

	void delete(Restaurant entity);

	List<Restaurant> getAll(int currentPage);
	
	List<Restaurant> getAll();

	int getPageTotal();

	List<Restaurant> getByCompositeQuery(Map<String, String[]> map);
}
