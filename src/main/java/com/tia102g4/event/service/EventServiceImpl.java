package com.tia102g4.event.service;

import java.util.List;

import com.tia102g4.event.dao.EventDAO;
import com.tia102g4.event.dao.EventDAOImpl;
import com.tia102g4.event.model.Event;

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

}
