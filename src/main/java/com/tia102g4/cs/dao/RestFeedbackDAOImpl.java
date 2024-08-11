package com.tia102g4.cs.dao;

import static com.tia102g4.util.Constants.PAGE_MAX_RESULT;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

import com.tia102g4.cs.model.CustomerService;
import com.tia102g4.rest.model.Restaurant;
import com.tia102g4.util.HibernateUtil;

public class RestFeedbackDAOImpl implements RestFeedbackDAO {
	private SessionFactory factory;

	public RestFeedbackDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(Integer feedbackType, String feedbackContent) {
		CustomerService cs = new CustomerService();
		Restaurant rest = getSession().get(Restaurant.class, 1L);
		cs.setFeedbackType(feedbackType);
		cs.setFeedbackContent(feedbackContent);
		cs.setFeedbackTime(new Timestamp(System.currentTimeMillis()));
		cs.setRestaurant(rest);
		cs.setReplyStatus(false);
		getSession().save(cs);
	}

	@Override
	public void delete(Long csId, Boolean deletedRest) {
		Query query = getSession()
				.createQuery("UPDATE CustomerService SET deletedRest = :deletedRest WHERE csId = :csId");
		query.setParameter("deletedRest", deletedRest);
		query.setParameter("csId", csId);
		query.executeUpdate();
	}

	@Override
	public List<CustomerService> getByCompositeQuery(Map<String, String> map) {
		Restaurant rest = getSession().get(Restaurant.class, 1L);
		// 創建各種查詢條件
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		// 指定查詢的返回類型為CustomerService
		CriteriaQuery<CustomerService> criteria = builder.createQuery(CustomerService.class);
		// 表示要查詢的類型為CustomerService
		Root<CustomerService> root = criteria.from(CustomerService.class);
		// 創建用於存放查詢條件的集合
		List<Predicate> predicates = new ArrayList<>();

		if (map.containsKey("replyStatus")) {
			// 取得 replyStatus 的值並轉換為 Boolean
			Boolean replyStatus = Boolean.parseBoolean(map.get("replyStatus"));
			predicates.add(builder.equal(root.get("replyStatus"), replyStatus));
		}

		if (map.containsKey("feedbackTime")) {
			// 將輸入的日期轉為LocalDate
			LocalDate feedbackDate = LocalDate.parse(map.get("feedbackTime"));

			// 設置一天的開始和結束時間
			LocalDateTime startOfDay = feedbackDate.atStartOfDay();
			LocalDateTime endOfDay = feedbackDate.atTime(23, 59, 59);

			// 轉換為Timestamp
			java.sql.Timestamp startTimestamp = java.sql.Timestamp.valueOf(startOfDay);
			java.sql.Timestamp endTimestamp = java.sql.Timestamp.valueOf(endOfDay);

			// 輸入意見反應時間比對，查詢範圍從一天的開始到一天的結束
			predicates.add(builder.between(root.get("feedbackTime"), startTimestamp, endTimestamp));
		}

		for (Map.Entry<String, String> row : map.entrySet()) {
			// 餐廳名稱模糊查詢
			if ("searchQuery".equals(row.getKey())) {
				predicates.add(builder.like(root.get("feedbackContent"), "%" + row.getValue() + "%"));
			} else if ("feedbackType".equals(row.getKey())) {
				int feedbackType = Integer.parseInt(row.getValue());
				predicates.add(builder.equal(root.get("feedbackType"), feedbackType));
			}
		}
		if (rest != null) {
			// 加個條件只顯示用戶自己本身的資料
			predicates.add(builder.equal(root.get("restaurant"), rest));
		}
		// member 為 NULL
		predicates.add(builder.isNull(root.get("member")));
		// 只查詢未刪除的資料
		predicates.add(builder.isFalse(root.get("deletedRest")));
		// 將所有的predicates 條件使用AND邏輯組合,並設置為 criteria的WHERE條件
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		// 針對ID做升冪排序
		criteria.orderBy(builder.asc(root.get("csId")));
		TypedQuery<CustomerService> query = getSession().createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public List<CustomerService> getAll(int currentPage) {
		Long rest = 1L;
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery(
				"SELECT cs FROM CustomerService cs LEFT JOIN fetch cs.restaurant r LEFT JOIN fetch cs.admin WHERE cs.member IS NULL AND cs.deletedRest = false AND r.restId = :restId",
				CustomerService.class).setParameter("restId", rest).setFirstResult(first).setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	@Override
	public long getTotal() {
		Long restId = 1L;
		return getSession().createQuery(
				"SELECT COUNT(cs) FROM CustomerService cs WHERE cs.member IS NULL AND cs.deletedRest = false AND cs.restaurant.restId = :restId",
				Long.class).setParameter("restId", restId).uniqueResult();
	}
}
