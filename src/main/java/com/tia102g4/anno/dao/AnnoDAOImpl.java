package com.tia102g4.anno.dao;

import static com.tia102g4.util.Constants.PAGE_MAX_RESULT;

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
import org.hibernate.query.Query;

import com.tia102g4.anno.model.Anno;
import com.tia102g4.util.HibernateUtil;

public class AnnoDAOImpl implements AnnoDAO {

	private SessionFactory factory;

	public AnnoDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Long insert(Anno entity) {
		return (Long) getSession().save(entity);
	}

	@Override
	public Long update(Anno entity) {
		try {
			getSession().update(entity);
			return 1L;
		} catch (Exception e) {
			return -1L;
		}
	}

	@Override
	public void delete(Anno entity) {
		String deleted = "UPDATE Anno a SET a.deleted = :deleted WHERE a.annoId = :annoId";
		Query query = getSession().createQuery(deleted);
		query.setParameter("deleted", entity.getDeleted());
		query.setParameter("annoId", entity.getAnnoId());
		query.executeUpdate();
	}

	@Override
	public List<Anno> getAll() {
		return getSession().createQuery("from Anno where deleted = false", Anno.class).list();
	}

	@Override
	public List<Anno> getByCompositeQuery(Map<String, String> map) {
		if (map.size() == 0)
			return getAll();

		// 創建各種查詢條件
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		// 指定查詢的返回類型為Anno
		CriteriaQuery<Anno> criteria = builder.createQuery(Anno.class);
		// 表示要查詢的類型為Anno
		Root<Anno> root = criteria.from(Anno.class);
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
			}
		}
		// 只能查詢沒有刪除的資料
		predicates.add(builder.isFalse(root.get("deleted")));
		// 將所有的 predicates 條件使用 AND 邏輯組合，並設置為 criteria 的 WHERE 條件
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		// 針對ID做升冪排序
		criteria.orderBy(builder.asc(root.get("annoId")));
		TypedQuery<Anno> query = getSession().createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public List<Anno> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("from Anno where deleted = false", Anno.class).setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT).list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from Anno where deleted = false", Long.class).uniqueResult();
	}

}
