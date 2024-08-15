package com.tia102g4.rest.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tia102g4.rest.model.Restaurant;
import com.tia102g4.util.HibernateUtil;
import com.tia102g4.util.Constants;

public class RestaurantDAOImpl implements RestaurantDAO, Constants{
	private SessionFactory factory;
	
	public RestaurantDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	private Session getSession() {
		return factory.openSession();
	}

	@Override
	public long insert(Restaurant entity) {
		return (Long) getSession().save(entity);
	}

	@Override
	public long update(Restaurant entty) {
		try {
			getSession().update(entty);
			return 1L;
		}catch(Exception e) {
			return -1L;
		}
		
	}
	
	@Override
	public long delete(Long id) {
		Restaurant entity = getSession().get(Restaurant.class, id);
		if ( entity != null ) {
			getSession().delete(entity);
			return 1L;
		}else {
		return -1L;
		}
	}

	@Override
	public Restaurant getById(Long id) {
		return getSession().get(Restaurant.class, id);
	}

	@Override
	public List<Restaurant> getAll() {
		return getSession().createQuery("from Restaurant", Restaurant.class).list();
	}

	@Override
	public List<Restaurant> getByCompositeQuery(Map<String, String> map) {
		if( map.size() == 0 ) {
			return getAll();
		}
		
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
		Root<Restaurant> root = criteria.from(Restaurant.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		for(Map.Entry<String, String> row : map.entrySet()) {
			
			if("restName".equals(row.getKey())) {
				predicates.add(builder.like(root.get("restName"), "%" + row.getValue()+"%"));//餐廳名稱做模糊查詢
			}
			
			if("restType".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("restType"), row.getValue())); //餐廳類別做查詢
			}
			
		}
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("restId")));
		TypedQuery<Restaurant> query = getSession().createQuery(criteria);
		
		return query.getResultList();
	}

	@Override
	public List<Restaurant> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("from Restaurant", Restaurant.class)
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from Restaurant", Long.class).uniqueResult();
	}

	

}
