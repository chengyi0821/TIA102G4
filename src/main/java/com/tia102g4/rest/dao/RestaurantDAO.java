package com.tia102g4.rest.dao;

import java.util.List;
import java.util.Map;

import com.tia102g4.rest.model.Restaurant;

public interface RestaurantDAO {
	void insert(Restaurant entity, Long restType);

	void update(Restaurant entity);

	void delete(Restaurant entity);

	Restaurant findAccountByUser(Restaurant entity);

	List<Restaurant> getByCompositeQuery(Map<String, String> map);

	List<Restaurant> getAll(int currentPage);

	List<Restaurant> getAll();

	long getTotal();
}
