package com.tia102g4.room.dao;

import java.util.List;

import com.tia102g4.member.model.Member;
import com.tia102g4.room.model.Room;

public interface Room2DAO {
	
	    Long createRoom(Room entity);
	    
	    Room getRoomByQrCode(String inviteCode);
	    
	    boolean InviteCodeValid(String inviteCode);
	    
	    void updateRoomStatus(Long roomId, boolean status);
	    
	    List<Member> getMembersByJoinTime(Long roomId);
	}







	
	
	
    
	

