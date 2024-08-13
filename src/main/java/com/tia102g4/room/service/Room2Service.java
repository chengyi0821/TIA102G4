package com.tia102g4.room.service;

import java.util.List;

import com.tia102g4.member.model.Member;
import com.tia102g4.room.model.Room;

public interface Room2Service {
	
	    Long createRoom(Room entity);
	    
	    Room getRoomByQrCode(String inviteCode);
	    
	    boolean InviteCodeValid(String inviteCode);
	    
	    void updateRoomStatus(Long roomId, boolean status);
	    
	    List<Member> getMembersByJoinTime(Long roomId);
	}

