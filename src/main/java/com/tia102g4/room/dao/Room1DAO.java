package com.tia102g4.room.dao;

import com.tia102g4.room.model.Room;

public interface Room1DAO {
	
	 Long createRoom(Room entity);
	 
	 Room getRoomByQrCode(String inviteCode);
	 
	 boolean InviteCodeValid(String inviteCode);	    
	   
	}
