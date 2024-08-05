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
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tia102g4.admin.model.Admin;
import com.tia102g4.cs.model.CustomerService;
import com.tia102g4.member.model.Member;
import com.tia102g4.util.HibernateUtil;

public class CSRestDAOImpl implements CSDAO {

	private SessionFactory factory;

	public CSRestDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}
	@Override
	public void update(Long csId, Integer replyHeading, String replyContent, Timestamp replyTime, Boolean replyStatus,
			Long adminId) {
		Query query =getSession().createQuery("UPDATE CustomerService SET replyHeading = :replyHeading, replyContent = :replyContent, replyStatus = :replyStatus,replyTime = :replyTime ,admin = :admin WHERE csId = :csId");
		Admin admin = getSession().get(Admin.class, adminId);
		query.setParameter("replyHeading", replyHeading);
		query.setParameter("replyContent", replyContent);
		query.setParameter("replyStatus", replyStatus);
		query.setParameter("replyTime", replyTime);
		query.setParameter("admin", admin);
		query.setParameter("csId", csId);
		query.executeUpdate();
	}
	
	@Override
	public void delete(Long csId, Boolean deletedAdmin) {
		Query query =getSession().createQuery("UPDATE CustomerService SET deletedAdmin = :deletedAdmin WHERE csId = :csId");
		query.setParameter("deletedAdmin", deletedAdmin);
		query.setParameter("csId", csId);
		query.executeUpdate();
	}
	
	public List<CustomerService> getByCompositeQuery(Map<String, String> map) {
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
					// 會員名稱模糊查詢
					if ("searchQuery".equals(row.getKey())) {
						Join<CustomerService, Member> memberJoin = root.join("restaurant");
						predicates.add(builder.like(memberJoin.get("restName"), "%" + row.getValue() + "%"));
					} else if ("feedbackType".equals(row.getKey())) {
						int feedbackType = Integer.parseInt(row.getValue());
						predicates.add(builder.equal(root.get("feedbackType"), feedbackType));
					}
				}
				// member 為 NULL
				predicates.add(builder.isNull(root.get("member")));
				// 只查詢未刪除的資料
				predicates.add(builder.isFalse(root.get("deletedAdmin")));
				// 將所有的predicates 條件使用AND邏輯組合,並設置為 criteria的WHERE條件
				criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
				// 針對ID做升冪排序
				criteria.orderBy(builder.asc(root.get("csId")));
				TypedQuery<CustomerService> query = getSession().createQuery(criteria);
				return query.getResultList();
	}

	public List<CustomerService> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery(
				"SELECT cs FROM CustomerService cs LEFT JOIN fetch cs.restaurant r LEFT JOIN fetch cs.admin WHERE cs.member IS NULL AND cs.deletedAdmin = false",
				CustomerService.class).setFirstResult(first).setMaxResults(PAGE_MAX_RESULT).list();
	}
	public long getTotal() {
		return getSession().createQuery(
				"SELECT COUNT(*) FROM CustomerService cs WHERE cs.member IS NULL AND cs.deletedAdmin = false",
				Long.class).uniqueResult();
	}

}
