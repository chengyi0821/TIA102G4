package com.tia102g4.member.dao;


    import static com.tia102g4.util.Constants.PAGE_MAX_RESULT;
	import java.util.List;
	import java.util.Map;
	import java.util.ArrayList;

	import javax.persistence.TypedQuery;
	import javax.persistence.criteria.CriteriaBuilder;
	import javax.persistence.criteria.CriteriaQuery;
	import javax.persistence.criteria.Predicate;
	import javax.persistence.criteria.Root;

	import org.hibernate.Session;
	import org.hibernate.SessionFactory;
	import org.hibernate.query.Query;

	import com.tia102g4.member.model.Member;
    import com.tia102g4.util.HibernateUtil;


	public class MemberDAOImp1 implements MemberDAO {

		private SessionFactory factory;

		public MemberDAOImp1() {
			factory = HibernateUtil.getSessionFactory();
		}

		private Session getSession() {
			return factory.getCurrentSession();
		}

		@Override
		public long insert(Member entity) {
			return (Long) getSession().save(entity);
		}

		@Override
		public void updatePassword(Member entity) {
			Query query = getSession().createQuery("UPDATE Member m SET m.password = :password WHERE m.email = :email");
			query.setParameter("email", entity.getEmail());
			query.setParameter("password", entity.getPassword());
			query.executeUpdate();
		}

		@Override
		public long delete(Long id) {
			Member member = getSession().get(Member.class, id);
			if (member != null) {
				getSession().delete(member);
				return 1L;
			} else {
				return -1L;

			}
		}

		@Override
		public Member getById(Long id) {
			return getSession().get(Member.class, id);
		}

		@Override
		public List<Member> getAll() {
			return getSession().createQuery("from Member", Member.class).list();
		}

		@Override
		public List<Member> getByCompositeQuery(Map<String, String> map) {
			if (map.size() == 0) {
				return getAll();
			}
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaQuery<Member> criteria = builder.createQuery(Member.class);
			Root<Member> root = criteria.from(Member.class);

			List<Predicate> predicates = new ArrayList<>();

			for (Map.Entry<String, String> row : map.entrySet()) {

				if ("name".equals(row.getKey())) {
					predicates.add(builder.like(root.get("name"), "%" + row.getValue() + "%"));
				}

				if ("account".equals(row.getKey())) {
					predicates.add(builder.like(root.get("account"), "%" + row.getValue() + "%"));
				}
			}

			criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
			criteria.orderBy(builder.asc(root.get("memberId")));
			TypedQuery<Member> query = getSession().createQuery(criteria);

			return query.getResultList();
		}

		@Override
		public List<Member> getAll(int currentPage) {
			int first = (currentPage - 1) * PAGE_MAX_RESULT;
			try {
				return getSession().createQuery("from Member", Member.class).setFirstResult(first)
						.setMaxResults(PAGE_MAX_RESULT).list();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return getSession().createQuery("from Member", Member.class).setFirstResult(first)
			.setMaxResults(PAGE_MAX_RESULT).list();
		}

		@Override
		public long getTotal() {
			return getSession().createQuery("select count(*) from Member", Long.class).uniqueResult();
		}

		@Override
		public Member findByEmailAndPassword(String email, String password) {
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaQuery<Member> criteria = builder.createQuery(Member.class);
			Root<Member> root = criteria.from(Member.class);

			criteria.where(builder.equal(root.get("email"), email), builder.equal(root.get("password"), password));
			TypedQuery<Member> query = getSession().createQuery(criteria);
			List<Member> result = query.getResultList();
			return result.isEmpty() ? null : result.get(0);
		}

		@Override
		public boolean findForPasswordReset(String email) {
				return getSession().createQuery("SELECT count(1) FROM Member WHERE email = :email" ,Long.class)
								   .setParameter("email",email).getSingleResult() > 0;
		}

		@Override
		public boolean isDuplicateAccount(String account) {
			return !getSession().createQuery("from Member where account = :account", Member.class)
					.setParameter("account", account).getResultList().isEmpty();
		}

		@Override
		public boolean isDuplicateEmail(String email) {
			return !getSession().createQuery("from Member where email = :email", Member.class).setParameter("email", email)
					.getResultList().isEmpty();

		}

		@Override
		public boolean isDuplicateMobileNo(String mobileNo) {
			return !getSession().createQuery("from Member where mobileNo = :mobileNo", Member.class)
					.setParameter("mobileNo", mobileNo).getResultList().isEmpty();
		}

		@Override
		public Member findfindByNameEmailAndMobile(String name, String email, String mobileNo) {
			String hql = "FROM Member WHERE name = :name AND email = :email AND mobileNo = :mobileNo";
			Query<Member> query = getSession().createQuery(hql, Member.class);
			query.setParameter("name", name);
			query.setParameter("email", email);
			query.setParameter("mobileNo", mobileNo);
			return query.uniqueResult(); // 返回唯一结果，如果找到则返回 Member 对象，否则返回 null
		}

		@Override
		public void update(Member entity) {
			getSession().update(entity);
		}
	}

