package com.tia102g4.event.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tia102g4.event.model.Event;
import com.tia102g4.util.HibernateUtil;

public class EventDAOImpl implements EventDAO{
	
	private SessionFactory factory;
	
	public EventDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	private Session getSession() {
		return factory.openSession();
	}

	@Override
	public void addEvent(Event entity) {
		try {
		getSession().save(entity);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public List<Event> getByCode(String code) { //不使用PK去查詢資料，使用HQL
		String hql = "FROM Event e WHERE e.code = :code";
	    return getSession().createQuery(hql , Event.class)
	            .setParameter("code", code)
	            .list();
	    
	}

}
