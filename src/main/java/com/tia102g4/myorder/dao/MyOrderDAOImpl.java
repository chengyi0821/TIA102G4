package com.tia102g4.myorder.dao;

import java.sql.Date;
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
import org.hibernate.Transaction;

import com.tia102g4.myorder.model.MyOrder;
import com.tia102g4.util.HibernateUtil;

public class MyOrderDAOImpl implements MyOrderDAO {

	private SessionFactory factory;

	public MyOrderDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(MyOrder entity) {
		return (Integer) getSession().save(entity);
	}

	@Override
	public int update(MyOrder entity) {
		try {
			getSession().update(entity);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer orderId) {
		MyOrder myorder = getSession().get(MyOrder.class, orderId);
		if (myorder != null) {
			getSession().delete(myorder);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public MyOrder getById(Long orderId) {
		return getSession().get(MyOrder.class, orderId);
	}

	@Override
	public MyOrder getById1(Long orderId, Long restId) {
	    try {
	        return getSession()
	            .createQuery("FROM MyOrder WHERE orderId = :orderId AND restaurant.restId = :restId", MyOrder.class)
	            .setParameter("orderId", orderId)
	            .setParameter("restId", restId)
	            .uniqueResult();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	@Override
	public List<MyOrder> getAll() {
		return getSession().createQuery("from MyOrder order by orderId desc", MyOrder.class).list();
	}

	@Override
	public List<MyOrder> getByCompositeQuery(Map<String, String> map) {
		if (map.size() == 0)
			return getAll();

		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<MyOrder> criteria = builder.createQuery(MyOrder.class);
		Root<MyOrder> root = criteria.from(MyOrder.class);

		List<Predicate> predicates = new ArrayList<>();

		for (Map.Entry<String, String> row : map.entrySet()) {

			if ("orderId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("orderId"), row.getValue()));
			}

			if ("memberName".equals(row.getKey())) {
				predicates.add(builder.like(root.get("memberName"), "%" + row.getValue() + "%"));
			}

			if ("reserDate".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("reserDate"), Date.valueOf(row.getValue())));
			}
		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("orderId")));
		TypedQuery<MyOrder> query = getSession().createQuery(criteria);

		return query.getResultList();
	}

	@Override
	public List<MyOrder> getAll(int currentPage, int recordsPerPage) {
		int first = (currentPage - 1) * recordsPerPage;
		return getSession().createQuery("from MyOrder order by orderId desc", MyOrder.class).setFirstResult(first)
				.setMaxResults(recordsPerPage).list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from MyOrder order by orderId desc", Long.class).uniqueResult();
	}

	// ================================orderstatus1===============================================
	@Override
	public List<MyOrder> getOrderStatus1(int currentPage, int recordsPerPage) {
		int first = (currentPage - 1) * recordsPerPage;
		return getSession().createQuery("from MyOrder where orderStatus = :orderStatus order by orderId desc", MyOrder.class)
				.setParameter("orderStatus", "1").setFirstResult(first).setMaxResults(recordsPerPage).list();
	}

	@Override
	public long getOrderStatus1Total() {
		return getSession().createQuery("select count(*) from MyOrder where orderStatus = :orderStatus order by orderId desc", Long.class)
				.setParameter("orderStatus", "1").uniqueResult();
	}

	// ================================orderstatus2===============================================
	@Override
	public List<MyOrder> getOrderStatus2(int currentPage, int recordsPerPage) {
		int first = (currentPage - 1) * recordsPerPage;
		return getSession().createQuery("from MyOrder where orderStatus = :orderStatus order by orderId desc", MyOrder.class)
				.setParameter("orderStatus", "2").setFirstResult(first).setMaxResults(recordsPerPage).list();
	}

	@Override
	public long getOrderStatus2Total() {
		return getSession().createQuery("select count(*) from MyOrder where orderStatus = :orderStatus order by orderId desc", Long.class)
				.setParameter("orderStatus", "2").uniqueResult();
	}

	// ================================orderstatus3===============================================
	@Override
	public List<MyOrder> getOrderStatus3(int currentPage, int recordsPerPage) {
		int first = (currentPage - 1) * recordsPerPage;
		return getSession().createQuery("from MyOrder where orderStatus = :orderStatus order by orderId desc", MyOrder.class)
				.setParameter("orderStatus", "3").setFirstResult(first).setMaxResults(recordsPerPage).list();
	}

	@Override
	public long getOrderStatus3Total() {
		return getSession().createQuery("select count(*) from MyOrder where orderStatus = :orderStatus order by orderId desc", Long.class)
				.setParameter("orderStatus", "3").uniqueResult();
	}

	// ======================================update
	// orderstautus===================================================
	@Override
	public void updateOrderStatus(Long orderId, String status) {
		try {

			MyOrder order = getSession().get(MyOrder.class, orderId);
			if (order != null) {
				order.setOrderStatus(status);
				getSession().update(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateOrderStatus3(Long orderId, String status) {
		try {

			MyOrder order = getSession().get(MyOrder.class, orderId);
			if (order != null) {
				order.setOrderStatus(status);
				getSession().update(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//    ======================================餐廳的 start=============================================

	@Override
	public List<MyOrder> getAllByRestaurantId(Long restId, int currentPage, int recordsPerPage) {
		int first = (currentPage - 1) * recordsPerPage;
		return getSession().createQuery("from MyOrder where restaurant.restId = :restId order by orderId desc", MyOrder.class)
				.setParameter("restId", restId).setFirstResult(first).setMaxResults(recordsPerPage).list();
	}

	@Override
	public long getTotalByRestaurantId(Long restId) {
		return getSession().createQuery("select count(*) from MyOrder where restaurant.restId = :restId order by orderId desc", Long.class)
				.setParameter("restId", restId).uniqueResult();
	}

	@Override
	public List<MyOrder> getByCompositeQueryByRestId(Map<String, String> map, Long restId) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<MyOrder> criteria = builder.createQuery(MyOrder.class);
		Root<MyOrder> root = criteria.from(MyOrder.class);

		List<Predicate> predicates = new ArrayList<>();

		// 餐厅ID过滤
		predicates.add(builder.equal(root.get("restaurant").get("restId"), restId));

		for (Map.Entry<String, String> row : map.entrySet()) {
			if ("orderId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("orderId"), row.getValue()));
			}

			if ("memberName".equals(row.getKey())) {
				predicates.add(builder.like(root.get("memberName"), "%" + row.getValue() + "%"));
			}

			if ("reserDate".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("reserDate"), Date.valueOf(row.getValue())));
			}
		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("orderId")));
		TypedQuery<MyOrder> query = getSession().createQuery(criteria);

		return query.getResultList();
	}

	@Override
	public List<MyOrder> getOrderStatus1Rest(Long restId, int currentPage, int recordsPerPage) {
		int first = (currentPage - 1) * recordsPerPage;
		return getSession()
				.createQuery("from MyOrder where restaurant.restId = :restId and orderStatus = :orderStatus order by orderId desc",
						MyOrder.class)
				.setParameter("restId", restId).setParameter("orderStatus", "1").setFirstResult(first)
				.setMaxResults(recordsPerPage).list();
	}

	@Override
	public long getOrderStatus1Total(Long restId) {
		return getSession().createQuery(
				"select count(*) from MyOrder where restaurant.restId = :restId and orderStatus = :orderStatus order by orderId desc",
				Long.class).setParameter("restId", restId).setParameter("orderStatus", "1").uniqueResult();
	}

	@Override
	public List<MyOrder> getOrderStatus2Rest(Long restId, int currentPage, int recordsPerPage) {
		int first = (currentPage - 1) * recordsPerPage;
		return getSession()
				.createQuery("from MyOrder where restaurant.restId = :restId and orderStatus = :orderStatus order by orderId desc",
						MyOrder.class)
				.setParameter("restId", restId).setParameter("orderStatus", "2").setFirstResult(first)
				.setMaxResults(recordsPerPage).list();
	}

	@Override
	public long getOrderStatus2Total(Long restId) {
		return getSession().createQuery(
				"select count(*) from MyOrder where restaurant.restId = :restId and orderStatus = :orderStatus order by orderId desc",
				Long.class).setParameter("restId", restId).setParameter("orderStatus", "2").uniqueResult();
	}

	@Override
	public List<MyOrder> getOrderStatus3Rest(Long restId, int currentPage, int recordsPerPage) {
		int first = (currentPage - 1) * recordsPerPage;
		return getSession()
				.createQuery("from MyOrder where restaurant.restId = :restId and orderStatus = :orderStatus order by orderId desc",
						MyOrder.class)
				.setParameter("restId", restId).setParameter("orderStatus", "3").setFirstResult(first)
				.setMaxResults(recordsPerPage).list();
	}

	@Override
	public long getOrderStatus3Total(Long restId) {
		return getSession().createQuery(
				"select count(*) from MyOrder where restaurant.restId = :restId and orderStatus = :orderStatus order by orderId desc",
				Long.class).setParameter("restId", restId).setParameter("orderStatus", "3").uniqueResult();
	}

	@Override
	public void updateOrderStatus2Rest(Long orderId, String status, Long restId) {
		try {
			MyOrder order = getSession()
					.createQuery("from MyOrder where orderId = :orderId and restaurant.restId = :restId order by orderId desc", MyOrder.class)
					.setParameter("orderId", orderId).setParameter("restId", restId).uniqueResult();
			if (order != null) {
				order.setOrderStatus(status);
				getSession().update(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void updateOrderStatus3Rest(Long orderId, String status, Long restId) {
	    try {
	        MyOrder order = getSession()
	                .createQuery("from MyOrder where orderId = :orderId and restaurant.restId = :restId order by orderId desc", MyOrder.class)
	                .setParameter("orderId", orderId)
	                .setParameter("restId", restId)
	                .uniqueResult();
	        if (order != null) {
	            order.setOrderStatus(status);
	            getSession().update(order);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    }
	}

////    ====================================================Member========================================================

	@Override
	public List<MyOrder> getOrderStatus1Member(Long memberId) {
		try {
			List<MyOrder> orders = getSession()
					.createQuery("from MyOrder o where o.member.memberId = :memberId and orderStatus = :orderStatus order by orderId desc",
							MyOrder.class)
					.setParameter("memberId", memberId).setParameter("orderStatus", "1").list();
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<MyOrder> getOrderStatus2Member(Long memberId) {
		try {
			List<MyOrder> orders = getSession()
					.createQuery("from MyOrder o where o.member.memberId = :memberId and orderStatus = :orderStatus order by orderId desc",
							MyOrder.class)
					.setParameter("memberId", memberId).setParameter("orderStatus", "2").list();
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<MyOrder> getOrderStatus3Member(Long memberId) {
		try {
			List<MyOrder> orders = getSession()
					.createQuery("from MyOrder o where o.member.memberId = :memberId and orderStatus = :orderStatus order by orderId desc",
							MyOrder.class)
					.setParameter("memberId", memberId).setParameter("orderStatus", "3").list();
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void updateOrderStatus2Mem(Long orderId, String status, Long memberId) {
		try {
			MyOrder order = getSession()
					.createQuery("from MyOrder o where orderId = :orderId and o.member.memberId = :memberId order by orderId desc",
							MyOrder.class)
					.setParameter("orderId", orderId).setParameter("memberId", memberId).uniqueResult();
			if (order != null) {
				order.setOrderStatus(status);
				getSession().update(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void addOrder(MyOrder order) {
		try {
			getSession().save(order);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void updateOrder(MyOrder order) {
		try {
			getSession().update(order);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
