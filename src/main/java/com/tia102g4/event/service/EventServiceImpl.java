package com.tia102g4.event.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.tia102g4.util.Constants.PAGE_MAX_RESULT;
import com.tia102g4.event.dao.EventDAO;
import com.tia102g4.event.dao.EventDAOImpl;
import com.tia102g4.event.model.Event;
import com.tia102g4.rest.model.Restaurant;

public class EventServiceImpl implements EventService{
	
	private EventDAO dao;
	
	public EventServiceImpl() {
		dao = new EventDAOImpl();
	}

	@Override
	public void addEvent(Event entity) {
		 dao.addEvent(entity);
	}


	@Override  //想方法回傳東西讓Sesssion可以setAttribute();
	public List<Event> getInfo(String code) {
	 return	dao.getByCode(code);
		
	}

	@Override
	public List<Restaurant> getAllRestaurant(Restaurant entity) {
		return dao.getAllRestaurant(entity);
	}

	@Override
	public List<Restaurant> getAll(int currentPage) {
		return dao.getAll(currentPage);
	}

	@Override
	public List<Restaurant> getByCompositeQuery(Map<String, String[]> map) {
		Map<String, String> query = new HashMap<>();
		Set<Map.Entry<String, String[]>> entry = map.entrySet();
		
		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			if ("action".equals(key)) {
				continue;
			}
			String value = row.getValue()[0];
			if (value == null || value.isEmpty()) {
				continue;
			}
			query.put(key, value);
		}
		System.out.println(query);
		return dao.getByCompositeQuery(query);
	}

	@Override
	public long getPageTotal() {
		long total = dao.getTotal();
		int pageQty = (int)(total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;		
	}

	@Override
	public boolean validateCode(String code) {
		return dao.isEvent(code);
	}

}
