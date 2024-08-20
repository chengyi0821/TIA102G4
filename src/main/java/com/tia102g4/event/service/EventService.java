package com.tia102g4.event.service;

import java.util.List;
import java.util.Map;

import com.tia102g4.event.model.Event;
import com.tia102g4.rest.model.Restaurant;

public interface EventService {
	
	void addEvent(Event evt);
	
	List<Event> getInfo(String code);
	
	List<Restaurant> getAllRestaurant(Restaurant entity);
	
	List<Restaurant> getAll(int currentPage);
	
	List<Restaurant>getByCompositeQuery(Map<String, String[]> map);
	
	long getPageTotal();
	
	boolean validateCode(String code);
	
}
