package com.tia102g4.restNews.dao;

import static com.tia102g4.util.Constants.PAGE_MAX_RESULT;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tia102g4.rest.model.Restaurant;
import com.tia102g4.restNews.model.RestaurantNews;
import com.tia102g4.util.HibernateUtil;

public class RestNewsDAOImpl implements RestNewsDAO {

	private SessionFactory factory;

	public RestNewsDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(RestaurantNews entity, Long restId) {
		Restaurant rest = getSession().get(Restaurant.class, restId);
		entity.setRestaurant(rest);
		getSession().save(entity);
	}

	@Override
	public Long update(RestaurantNews entity, Long restId) {
		try {
			Restaurant rest = getSession().get(Restaurant.class, restId);
			entity.setRestaurant(rest);
			getSession().update(entity);
			return 1L;
		} catch (Exception e) {
			return -1L;
		}
	}

	@Override
	public void delete(RestaurantNews entity) {
		String deleted = "UPDATE RestaurantNews r SET r.deleted = :deleted WHERE r.newsId = :newsId";
		Query query = getSession().createQuery(deleted);
		query.setParameter("deleted", entity.getDeleted());
		query.setParameter("newsId", entity.getNewsId());
		query.executeUpdate();
	}
	
	@Override
	public List<RestaurantNews> getByCompositeQuery(Map<String, String> map, Long restId) {
		Restaurant rest = getSession().get(Restaurant.class, restId);
		// 創建各種查詢條件
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		// 指定查詢的返回類型為RestaurantNews
		CriteriaQuery<RestaurantNews> criteria = builder.createQuery(RestaurantNews.class);
		// 表示要查詢的類型為RestaurantNews
		Root<RestaurantNews> root = criteria.from(RestaurantNews.class);
		// 創建用於存放查詢條件的集合
		List<Predicate> predicates = new ArrayList<>();

		// 起始日期跟結束日期之間的查詢
		if (map.containsKey("startDate") && map.containsKey("endDate")) {

			// 如果startDate大於或等於map中的startDate
			Predicate startDate = builder.greaterThanOrEqualTo(root.get("startDate"),
					Date.valueOf(map.get("startDate")));
			// 如果endDate小於或等於map中的endDate
			Predicate endDate = builder.lessThanOrEqualTo(root.get("endDate"), Date.valueOf(map.get("endDate")));
			predicates.add(builder.and(startDate, endDate));
		} else {
			// 只輸入startdate的話
			if (map.containsKey("startDate")) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("startDate"), Date.valueOf(map.get("startDate"))));
			}
			// 只輸入enddate的話
			if (map.containsKey("endDate")) {
				predicates.add(builder.lessThanOrEqualTo(root.get("endDate"), Date.valueOf(map.get("endDate"))));
			}
		}
		for (Map.Entry<String, String> row : map.entrySet()) {
			// 主旨模糊搜尋
			if ("searchQuery".equals(row.getKey())) {
				predicates.add(builder.like(root.get("heading"), "%" + row.getValue() + "%"));
			} else if ("type".equals(row.getKey())) {
				int type = Integer.parseInt(row.getValue());
				predicates.add(builder.equal(root.get("type"), type));
			}
		}
		if (rest != null) {
			// 設定餐廳使用者查找自己的最新消息,所以設定自己的id
			predicates.add(builder.equal(root.get("restaurant"), rest));
		}
		// 只能查詢沒有刪除的資料
		predicates.add(builder.isFalse(root.get("deleted")));
		// 將所有的 predicates 條件使用 AND 邏輯組合，並設置為 criteria 的 WHERE 條件
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		// 針對ID做升冪排序
		criteria.orderBy(builder.asc(root.get("newsId")));
		TypedQuery<RestaurantNews> query = getSession().createQuery(criteria);
		return query.getResultList();
	}
	
	@Override
	public boolean isOverlappingPeriods(RestaurantNews restaurantNews, Long restId) {
		return getSession().createQuery("SELECT count(1) FROM RestaurantNews WHERE restaurant.restId = :restId "
										+ "AND (:newsId IS NULL OR newsId != :newsId) "
										+ "AND (((startDate BETWEEN :startDate AND :endDate) OR (endDate BETWEEN :startDate AND :endDate)) "
										+ "OR (startDate <= :startDate AND endDate >= :endDate)) "
										+ "AND deleted = false", Long.class)
						   .setParameter("restId", restId)
	   					   .setParameter("newsId", restaurantNews.getNewsId())
	   					   .setParameter("startDate", restaurantNews.getStartDate(), TemporalType.DATE)
	   					   .setParameter("endDate", restaurantNews.getEndDate(), TemporalType.DATE)
	   					   .getSingleResult() > 0;
	}

	@Override
	public List<RestaurantNews> getAll(int currentPage, Long restId) {
		Restaurant rest = getSession().get(Restaurant.class, restId);
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession()
				.createQuery("from RestaurantNews where deleted = false AND restaurant = :restaurant",
						RestaurantNews.class)
				.setParameter("restaurant", rest).setFirstResult(first).setMaxResults(PAGE_MAX_RESULT).list();
	}

	@Override
	public List<RestaurantNews> getAll() {
		return getSession().createQuery("FROM RestaurantNews WHERE :now BETWEEN startDate AND endDate AND deleted = false ORDER BY newsId DESC", RestaurantNews.class)
						   .setParameter("now", new java.util.Date(), TemporalType.DATE)
						   .list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from RestaurantNews where deleted = false", Long.class)
				.uniqueResult();
	}

}
