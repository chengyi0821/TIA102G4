package com.tia102g4.event.service;

import com.tia102g4.event.model.Event;

public interface EventService {
	
	Event addEvent(Event evt);
	
	Event reduce(Event evt);
	
	Event changeLeader(Event evt);
	
}
