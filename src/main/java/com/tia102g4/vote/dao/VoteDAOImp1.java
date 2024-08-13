package com.tia102g4.vote.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


//import com.tia102g4.util.HibernateUtil;
import com.tia102g4.vote.model.Vote;

public class VoteDAOImp1 implements VoteDAO {
    
	
	
	
	private SessionFactory factory;
	
//	public VoteDAOImp1() {
//		factory = HibernateUtil.getSessionFactory();
//	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public int addVote(Vote entity) {
		return (Integer) getSession().save(entity);
	}

	@Override
	public int updateVote(Vote entity) {
		try {
			getSession().update(entity);
			return 1;
		}catch (Exception e) {
			return -1;
		}
	}


	@Override
	public Vote getById(Integer id) {
		return getSession().get(Vote.class, id);
	}

	@Override
	public List<Vote> getAll() {
		return getSession().createQuery("from Vote", Vote.class).list();
	}

	@Override
	public List<Vote> getByCompositeQuery(Map<String, String> map) {
		if (map.size()== 0) {
		return getAll();
	}
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Vote> criteria = builder.createQuery(Vote.class);
		Root<Vote> root = criteria.from(Vote.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(map.containsKey("lowercount")&& map.containsKey("uppercount")) //設定票數範圍查詢
			predicates.add(builder.between(root.get("count"), Integer.valueOf(map.get("lowercount")), Integer.valueOf(map.get("uppercount"))));
		
		for(Entry<String, String> row: map.entrySet() ) {
			if("type".equals(row.getKey())) {
				predicates.add(builder.like(root.get("type"), "%" + row.getValue() + "%"));//依照餐廳類別作模糊查詢
			}
			
			if("restaurant".equals(row.getKey())) {
				predicates.add(builder.like(root.get("restaurant"), "%" + row.getValue() + "%")); //依照餐廳名作模糊查詢
			}
			
			if("lowerrating".equals(row.getKey())) {
				if(!map.containsKey("uppercount"))
					predicates.add(builder.greaterThanOrEqualTo(root.get("count"), Integer.valueOf(row.getValue())));
			}
			
			if("upperrating".equals(row.getKey())) {
				if(!map.containsKey("lowercount"))
					predicates.add(builder.lessThanOrEqualTo(root.get("count"), Integer.valueOf(row.getValue())));
			}
			
			
		}
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("restaurant")));
		TypedQuery<Vote> query = getSession().createQuery(criteria);
		
		return query.getResultList();
		
	}

	
} 
	
	
			     
	

	
	


