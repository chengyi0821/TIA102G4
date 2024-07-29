package com.tia102g4.event.dao;

import com.tia102g4.event.model.Event;
public interface EventDAO {
	
	int addEvent(Event entity);//創立揪團
		
	int reduce(Event entity);//修改人數，只能減少
	
	int changeLeader(Event entity);//更換主揪

}
