package com.tia102g4.event.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tia102g4.event.model.Event;
//import com.tia102g4.util.HibernateUtil;

public class EventDAOImpl implements EventDAO{
	
	private SessionFactory factory;
	
//	public EventDAOImpl() {
//		factory = HibernateUtil.getSessionFactory();
//	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int addEvent(Event entity) {
		return (Integer) getSession().save(entity);
	}

	@Override
	public int reduce(Event entity) {
		try {
			getSession().update(entity);
			return 1;
		}catch(Exception e) {
			return -1;
		}
	}

	@Override
	public int changeLeader(Event entity) {
		try {
			getSession().update(entity);
			return 1;
		}catch(Exception e) {
			return -1;
		}
	}

}
