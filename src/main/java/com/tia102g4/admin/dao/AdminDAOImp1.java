package com.tia102g4.admin.dao;

import static com.tia102g4.util.Constants.PAGE_MAX_RESULT;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.tia102g4.admin.model.Admin;
import com.tia102g4.util.HibernateUtil;

public class AdminDAOImp1 implements AdminDAO {
	
	private SessionFactory factory;
	
	public AdminDAOImp1() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public long insert(Admin entity) {
		return (Long) getSession().save(entity);
	}

	@Override
	public void update(Admin entity) {
		try {
			getSession().update(entity);
		}catch(Exception e) {
			e.printStackTrace();
		}
		   
	}

	@Override
	public long delete(Long id) {
		Admin admin = getSession().get(Admin.class, id);
		if (admin != null) {
			getSession().delete(admin);
			return 1L;
		}else {
			return -1L;
		}
	}

	@Override
	public Admin getById(Long id) {
		return getSession().get(Admin.class, id);
	}

	@Override
	public List<Admin> getByCompositeQuery(Map<String, String> map) {
		if (map.size() == 0) {
			return getAll();
		}
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Admin> criteria = builder.createQuery(Admin.class);
		Root<Admin> root = criteria.from(Admin.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		for (Map.Entry<String, String> row : map.entrySet()) {
			
			if ("name".equals(row.getKey())) {
				predicates.add(builder.like(root.get("name"), "%" + row.getValue() + "%"));
			}
			if ("account".equals(row.getKey())) {
				predicates.add(builder.like(root.get("account"), "%" + row.getValue() + "%"));
			}
			if ("permission".equals(row.getKey())) {
	            int permissionValue = Integer.parseInt(row.getValue());
	            predicates.add(builder.equal(root.get("permission"), permissionValue));
	        }
			
		}
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("adminId")));
		TypedQuery<Admin> query = getSession().createQuery(criteria);
		
		return query.getResultList();
	}
            
	
	@Override
	public List<Admin> getAll(int currentPage) {
		int first = (currentPage -1) * PAGE_MAX_RESULT;
		try {
			return getSession().createQuery("from Admin", Admin.class).setFirstResult(first)
					.setMaxResults(PAGE_MAX_RESULT).list();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return getSession().createQuery("from Admin", Admin.class).setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT).list();
	}

	@Override
	public List<Admin> getAll() {
		return getSession().createQuery("from Admin", Admin.class).list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from Admin", Long.class).uniqueResult();
	}

	@Override
	public void updatePassword(Admin entity) {
		Query query = getSession().createQuery("UPDATE Admin a SET a.password = :password WHERE a.account = :account");
		query.setParameter("account", entity.getAccount());
	    query.setParameter("password", entity.getPassword());
	    query.executeUpdate();
	
	}

	@Override
	public Admin findByAccountAndPassword(String account, String password) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Admin> criteria = builder.createQuery(Admin.class);
		Root<Admin> root = criteria.from(Admin.class);
		
		criteria.where(builder.equal(root.get("account"), account), builder.equal(root.get("password"), password));
		TypedQuery<Admin> query = getSession().createQuery(criteria);
		List<Admin> result = query.getResultList();
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public boolean findForPasswordReset(String account) {
		return getSession().createQuery("SELECT count(1) FROM Admin WHERE account = :account" ,Long.class)
				   .setParameter("account",account).getSingleResult() > 0;
}
	

	
}
	
