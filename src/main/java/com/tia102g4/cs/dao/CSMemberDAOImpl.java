package com.tia102g4.cs.dao;

import static com.tia102g4.util.Constants.PAGE_MAX_RESULT;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tia102g4.cs.model.CustomerService;
import com.tia102g4.util.HibernateUtil;

public class CSMemberDAOImpl implements CSDAO {

	private SessionFactory factory;
	
	public CSMemberDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(CustomerService entity) {
		return 0;
	}

	@Override
	public int update(CustomerService entity) {
		return 0;
	}

	@Override
	public int delete(Integer id) {
		return 0;
	}

	@Override
	public List<CustomerService> getAll() {
		return getSession().createQuery(
				"SELECT cs FROM CustomerService cs LEFT JOIN fetch cs.restaurant r LEFT JOIN fetch cs.admin LEFT JOIN fetch cs.member m WHERE cs.restaurant IS NULL AND cs.deletedAdmin = false",
				CustomerService.class).list();
	}

	@Override
	public List<CustomerService> getByCompositeQuery(Map<String, String> map) {
		return null;
	}

	@Override
	public List<CustomerService> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery(
				"SELECT cs FROM CustomerService cs LEFT JOIN fetch cs.member m LEFT JOIN fetch cs.admin WHERE cs.restaurant IS NULL AND cs.deletedAdmin = false",
				CustomerService.class).setFirstResult(first).setMaxResults(PAGE_MAX_RESULT).list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery(
				"SELECT COUNT(*) FROM CustomerService cs WHERE cs.restaurant IS NULL AND cs.deletedAdmin = false",
				Long.class).uniqueResult();
	}

}
