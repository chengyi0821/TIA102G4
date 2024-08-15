package com.tia102g4.rest.dao;

import static com.tia102g4.util.Constants.PAGE_MAX_RESULT;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tia102g4.rest.model.Restaurant;
import com.tia102g4.util.HibernateUtil;

public class RestaurantDAOImpl implements RestaurantDAO {

	private SessionFactory factory;

	public RestaurantDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(Restaurant entity) {
	}

	@Override
	public void update(Restaurant entity) {
		String update = "UPDATE Restaurant r SET r.restName = :restName, r.email = :email, r.password = :password, r.phone = :phone, r.location = :location WHERE r.restId = :restId";
		Query query = getSession().createQuery(update);
		query.setParameter("restId", entity.getRestId());
		query.setParameter("restName", entity.getRestName());
		query.setParameter("email", entity.getEmail());
		query.setParameter("password", entity.getPassword());
		query.setParameter("phone", entity.getPhone());
		query.setParameter("location", entity.getLocation());
		query.executeUpdate();
	}

	@Override
	public void delete(Restaurant entity) {
		String delete = "UPDATE Restaurant r SET r.deleted = :deleted WHERE r.restId = :restId";
		Query query = getSession().createQuery(delete);
		System.out.println(entity.getRestId());
		System.out.println(entity.getDeleted());
		query.setParameter("restId", entity.getRestId());
		query.setParameter("deleted", entity.getDeleted());
		query.executeUpdate();
	}

	@Override
	public List<Restaurant> getByCompositeQuery(Map<String, String> map) {
		// 創建各種查詢條件
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		// 指定查詢的返回類型為Restaurant
		CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
		// 表示要查詢的類型為Restaurant
		Root<Restaurant> root = criteria.from(Restaurant.class);
		// 創建用於存放查詢條件的集合
		List<Predicate> predicates = new ArrayList<>();

		for (Map.Entry<String, String> row : map.entrySet()) {
			// 餐廳名稱/信箱/電話模糊搜尋
			if ("searchQuery".equals(row.getKey())) {
				predicates.add(builder.or(builder.like(root.get("restName"), "%" + row.getValue() + "%"),
						builder.like(root.get("email"), "%" + row.getValue() + "%"),
						builder.like(root.get("phone"), "%" + row.getValue() + "%")));
			}
		}
		// 只能查詢沒有刪除的資料
		predicates.add(builder.isFalse(root.get("deleted")));
		// 將所有的 predicates 條件使用 AND 邏輯組合，並設置為 criteria 的 WHERE 條件
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		// 針對ID做升冪排序
		criteria.orderBy(builder.asc(root.get("restId")));
		TypedQuery<Restaurant> query = getSession().createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public List<Restaurant> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("FROM Restaurant WHERE deleted = false", Restaurant.class).setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT).list();
	}

	@Override
	public List<Restaurant> getAll() {
		return null;
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from Restaurant where deleted = false", Long.class)
				.uniqueResult();
	}

}
