package com.tia102g4.rest.service;

import java.util.List;
import java.util.Map;

import javax.security.auth.login.LoginException;

import com.tia102g4.rest.model.Restaurant;
import com.tia102g4.rest.to.RestaurantReqTO;
import com.tia102g4.rest.to.RestaurantUpdateReqTO;

public interface RestaurantService {
	void create(RestaurantReqTO reqTO);

	void update(Restaurant entity);
	
	void updateForRest(RestaurantReqTO reqTO, Long restId);

	void delete(Restaurant entity);
	
	Restaurant findAccountByUser(Restaurant entity) throws LoginException;
	
	RestaurantReqTO findIdByUser(Long restId);

	List<Restaurant> getAll(int currentPage);
	
	List<Restaurant> getAll();

	int getPageTotal();

	List<Restaurant> getByCompositeQuery(Map<String, String[]> map);
	
}
