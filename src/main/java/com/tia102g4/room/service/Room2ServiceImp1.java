package com.tia102g4.room.service;

import java.util.List;

import com.tia102g4.member.model.Member;
import com.tia102g4.room.dao.Room2DAO;
import com.tia102g4.room.dao.Room2DAOImp1;
import com.tia102g4.room.model.Room;

public class Room2ServiceImp1 implements Room2Service {

	private Room2DAO dao;
	
	public Room2ServiceImp1() {
		dao = new Room2DAOImp1();
	}

	@Override
	public Long createRoom(Room entity) {
		return dao.createRoom(entity);
	}

	@Override
	public Room getRoomByQrCode(String inviteCode) {
		return dao.getRoomByQrCode(inviteCode);
	}

	@Override
	public boolean InviteCodeValid(String inviteCode) {
		return dao.InviteCodeValid(inviteCode);
	}

	@Override
	public void updateRoomStatus(Long roomId, boolean status) {
		dao.updateRoomStatus(roomId, status);
    }
		
	

	@Override
	public List<Member> getMembersByJoinTime(Long roomId) {
		return dao.getMembersByJoinTime(roomId);
	}
	
}
	
