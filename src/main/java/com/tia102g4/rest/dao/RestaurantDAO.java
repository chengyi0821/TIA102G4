package com.tia102g4.rest.dao;

import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.descriptor.web.LoginConfig;

import com.tia102g4.rest.model.Restaurant;
import com.tia102g4.vote.dao.VoteDAOImp1;

public interface RestaurantDAO {
	void insert(Restaurant entity, Long restType);

	void update(Restaurant entity);
	
	void updateForRest(Restaurant entity, Long restType);

	void delete(Restaurant entity);

	Restaurant findAccountByUser(Restaurant entity);

	List<Restaurant> getByCompositeQuery(Map<String, String> map);

	List<Restaurant> getAll(int currentPage);

	List<Restaurant> getAll();
	
	Restaurant findIdByUser(Long restId);

	long getTotal();
}
