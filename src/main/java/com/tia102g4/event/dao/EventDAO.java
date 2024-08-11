package com.tia102g4.event.dao;

import java.util.List;

import com.tia102g4.event.model.Event;
public interface EventDAO {
	
	void addEvent(Event entity);//創立揪團
				
	List<Event> getByCode(String code);//使用邀請碼查找活動資訊

}
