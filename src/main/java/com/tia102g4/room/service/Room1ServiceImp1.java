package com.tia102g4.room.service;


import com.tia102g4.room.dao.Room1DAO;
import com.tia102g4.room.dao.Room1DAOImp1;
import com.tia102g4.room.model.Room;

public class Room1ServiceImp1 implements Room1Service {

	private Room1DAO dao;
	
	public Room1ServiceImp1() {
		dao = new Room1DAOImp1();
	}

	@Override
	public Long createRoom(Room entity) {
		return dao.createRoom(entity);
	}

	@Override
	public Room getRoomByInviteCode(String inviteCode) {
		return dao.getRoomByQrCode(inviteCode);
	}

	@Override
	public boolean InviteCodeValid(String inviteCode) {
		
		return dao.InviteCodeValid(inviteCode);
	}

	

	

	

	
}
	