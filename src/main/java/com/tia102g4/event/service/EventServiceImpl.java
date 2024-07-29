package com.tia102g4.event.service;

import com.tia102g4.event.dao.EventDAO;
import com.tia102g4.event.dao.EventDAOImpl;
import com.tia102g4.event.model.Event;

public class EventServiceImpl implements EventService{
	
	private EventDAO dao;
	
	public EventServiceImpl() {
		dao = new EventDAOImpl();
	}

	@Override
	public Event addEvent(Event evt) {
		return null;
	}

	@Override
	public Event reduce(Event evt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event changeLeader(Event evt) {
		// TODO Auto-generated method stub
		return null;
	}

}
