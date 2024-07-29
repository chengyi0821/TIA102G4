package com.tia102g4.comment.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.*;
import com.tia102g4.comment.model.Comment;
//import com.tia102g4.restaurant.model.Restaurant;
//import com.tia102g4.util.HibernateUtil;

public class CommentDAOImpl implements CommentDAO{
	
	private SessionFactory factory;
	
//	public CommentDAOImpl() {
//		factory = HibernateUtil.getSessionFactory();
//	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int addComment(Comment entity) {
		return (Integer) getSession().save(entity);
	}

	@Override
	public int updateComment(Comment entity) {
		try {
			getSession().update(entity);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public List<Comment> getAll() {
		return getSession().createQuery("from Comment", Comment.class).list();
	}

	@Override
	public List<Comment> getByCompositeQuery(Map<String, String> map) {
		if (map.size()==0){
			return getAll();
		}
		
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Comment> criteria = builder.createQuery(Comment.class);
		Root<Comment> root = criteria.from(Comment.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(map.containsKey("lowerrating")&& map.containsKey("upperrating")) //設定評價星等範圍查詢
			predicates.add(builder.between(root.get("rating"), Integer.valueOf(map.get("lowerrating")), Integer.valueOf(map.get("upperrating"))));
		
		for(Entry<String, String> row: map.entrySet() ) {
			if("type".equals(row.getKey())) {
				predicates.add(builder.like(root.get("type"), "%" + row.getValue() + "%"));//依照餐廳類別作模糊查詢
			}
			
			if("restaurant".equals(row.getKey())) {
				predicates.add(builder.like(root.get("restaurant"), "%" + row.getValue() + "%")); //依照餐廳名作模糊查詢
			}
			
			if("lowerrating".equals(row.getKey())) {
				if(!map.containsKey("upperrating"))
					predicates.add(builder.greaterThanOrEqualTo(root.get("rating"), Integer.valueOf(row.getValue())));
			}
			
			if("upperrating".equals(row.getKey())) {
				if(!map.containsKey("lowerrating"))
					predicates.add(builder.lessThanOrEqualTo(root.get("rating"), Integer.valueOf(row.getValue())));
			}
			
			
		}
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("restaurant")));
		TypedQuery<Comment> query = getSession().createQuery(criteria);
		
		return query.getResultList();
		
	}

	
}
