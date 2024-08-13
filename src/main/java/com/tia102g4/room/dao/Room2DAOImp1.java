package com.tia102g4.room.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import com.tia102g4.member.model.Member;
import com.tia102g4.room.model.Room;
import com.tia102g4.util.HibernateUtil;

	public class Room2DAOImp1 implements Room2DAO{
		private SessionFactory factory;
		
	public Room2DAOImp1() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	private Session getSession() {
		return factory.getCurrentSession();

		
}

	@Override
	public Long createRoom(Room entity) {
		return (Long) getSession().save(entity);
	}

	@Override
	public Room getRoomByQrCode(String inviteCode) {
		return getSession().createQuery("FROM Room WHERE inviteCode = :inviteCode", Room.class)
				.setParameter("inviteCode", inviteCode)
				.uniqueResult();
	}

	@Override
	public boolean InviteCodeValid(String inviteCode) {
		Room room = getRoomByQrCode(inviteCode);
        return room != null;
    }

	@Override
	public void updateRoomStatus(Long roomId, boolean status) {
		    Room room = getSession().get(Room.class, roomId);
		    if (room != null) {
		        room.setStatus(Boolean.valueOf(status)); // 將 boolean 轉換為 Boolean
		        getSession().update(room);
		    }
		}

	@Override
	public List<Member> getMembersByJoinTime(Long roomId) {
		return getSession().createQuery("FROM Member WHERE roomId = :roomId ORDER BY joinTime ASC", Member.class)
                .setParameter("roomId", roomId)
                .list();
}
	}

	

	
	

	

	
