package com.tia102g4.blacklist.dao;

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

import com.tia102g4.blacklist.model.BlackList;
import com.tia102g4.util.HibernateUtil;

public class BlackListDAOImpl implements BlackListDAO {

	private SessionFactory factory;

	public BlackListDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

//	@Override
//	public int insert(BlackList blacklist) {
//		return ((Long) getSession().save(blacklist)).intValue();
//	}
//
	@Override
	public boolean isMemberInBlackList(Long memberId, Long restId) {
	    boolean result = false;
	    try {
	        Query<Long> query = getSession().createQuery(
	                "SELECT count(b) FROM BlackList b WHERE b.member.memberId = :memberId AND b.restaurant.restId = :restId",
	                Long.class);
	        query.setParameter("memberId", memberId);
	        query.setParameter("restId", restId);
	        Long count = query.uniqueResult();
	        result = count > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}

	@Override
	public int insert(BlackList blacklist) {
	    try {
	        return ((Long) getSession().save(blacklist)).intValue();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return -1;
	    }
	}

	@Override
	public int delete(Long blackListId) {
		BlackList blacklist = getSession().get(BlackList.class, blackListId);
		if (blacklist != null) {
			getSession().delete(blacklist);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public List<BlackList> getAll(Long restId) {
	    return getSession().createQuery("from BlackList where restaurant.restId= :restId order by blackListId desc", BlackList.class)
	                       .setParameter("restId", restId)
	                       .list();
	}


	@Override
	public List<BlackList> getByCompositeQuery(Map<String, String> map, Long restId) {
		if (map.size() == 0)
			return getAll(restId);

		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<BlackList> criteria = builder.createQuery(BlackList.class);
		Root<BlackList> root = criteria.from(BlackList.class);

		List<Predicate> predicates = new ArrayList<>();

		for (Map.Entry<String, String> row : map.entrySet()) {

			if ("blackListId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("blackListId"), Long.valueOf(row.getValue())));
			}

		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("blackListId")));
		TypedQuery<BlackList> query = getSession().createQuery(criteria);

		return query.getResultList();
	}

}
