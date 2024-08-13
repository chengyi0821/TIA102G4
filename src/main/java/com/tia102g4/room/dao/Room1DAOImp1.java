package com.tia102g4.room.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.tia102g4.room.model.Room;
import com.tia102g4.util.HibernateUtil;

public class Room1DAOImp1 implements Room1DAO{
	
   private SessionFactory factory;
   
   public Room1DAOImp1() {
	   factory = HibernateUtil.getSessionFactory();
   }
   private Session getSession() {
	   return factory.getCurrentSession();
   }
@Override
public Long createRoom(Room entity) {
	Session session = getSession();
	session.beginTransaction();
	try {
		Long id = (Long) session.save(entity);
		session.getTransaction().commit();
		return id;
	}catch(Exception e) {
		if(session.getTransaction()!= null)session.getTransaction().rollback();
		e.printStackTrace();
		return null;
	}
}
@Override
public Room getRoomByQrCode(String inviteCode) {
	Session session = getSession();
	session.beginTransaction();
	try {
		String hql ="FROM ROOM R WHERE R.inviteCode = :inviteCode";
		Query<Room> query = session.createQuery(hql, Room.class);
		query.setParameter("invitCode", inviteCode);
		Room room =query.uniqueResult();
		session.getTransaction().commit();
		return room;
	}catch(Exception e) {
		if(session.getTransaction() !=null) session.getTransaction().rollback();
		e.printStackTrace();
        return null;
	}
}
@Override
public boolean InviteCodeValid(String inviteCode) {
	 return getRoomByQrCode(inviteCode) != null;
}


	
}


	

	