package com.tia102g4.room.service;

import com.tia102g4.room.model.Room;

public interface Room1Service {
	
	 Long createRoom(Room entity);
	 
	 Room getRoomByInviteCode(String inviteCode);
	 
	 boolean InviteCodeValid(String inviteCode);
}


