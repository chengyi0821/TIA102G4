package com.tia102g4.event.dao;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import static com.tia102g4.util.Constants.PAGE_MAX_RESULT;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tia102g4.event.model.Event;
import com.tia102g4.member.model.Member;
import com.tia102g4.rest.model.Restaurant;
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

	@Override
	public List<Restaurant> getAllRestaurant(Restaurant entity) {
		return getSession().createQuery("from Restaurant", Restaurant.class).list();
	}

	@Override
	public List<Restaurant> getByCompositeQuery(Map<String, String> map) {
		 if( map.size() == 0 ) {
	            return getAllRestaurant(null);
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

	@Override
	public boolean isEvent(String code) {
		return !getSession().createQuery("from Event where code = :code", Event.class)
				.setParameter("code", code).getResultList().isEmpty();
	}

}
