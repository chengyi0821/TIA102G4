package com.tia102g4.event.service;

import java.util.List;

import com.tia102g4.event.model.Event;

public interface EventService {
	
	void addEvent(Event evt);
		
	
	List<Event> getInfo(String code);
	
}
