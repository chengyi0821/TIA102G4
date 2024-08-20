package com.tia102g4.event.dao;

import java.util.List;
import java.util.Map;

import com.tia102g4.event.model.Event;
import com.tia102g4.rest.model.Restaurant;
public interface EventDAO {
	
	void addEvent(Event entity);//創立揪團
				
	List<Event> getByCode(String code);//使用邀請碼查找活動資訊
    
    List<Restaurant> getAllRestaurant(Restaurant entity);
	
    List<Restaurant> getByCompositeQuery(Map<String, String> map);
    
    List<Restaurant> getAll(int currentPage);
    
    long getTotal();
    
    boolean isEvent(String code);
}
