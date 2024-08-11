package com.tia102g4.favorite.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.tia102g4.favorite.model.Favorite;
import com.tia102g4.util.HibernateUtil;

public class FavoriteDAOImpl implements FavoriteDAO {
	private SessionFactory factory;

	public FavoriteDAOImpl() {
		this.factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public List<Favorite> getFav(Long memberId) {
		List<Favorite> favorites = null;
		try {
			String hql = "FROM Favorite f WHERE f.member.memberId = :memberId";
			favorites = getSession().createQuery(hql, Favorite.class).setParameter("memberId", memberId).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return favorites;
	}

	public void insertFav(Favorite favorite) {
		try {
			getSession().save(favorite);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Favorite findByMemberIdAndRestId(Long memberId, Long restId) {
		try {
			String hql = "FROM Favorite f WHERE f.member.memberId = :memberId AND f.restaurant.restId = :restId";
			Query query = getSession().createQuery(hql);
			query.setParameter("memberId", memberId);
			query.setParameter("restId", restId);

			List<Favorite> results = query.list();

			if (results != null && !results.isEmpty()) {
				return results.get(0); 
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int deleteFav(Long memberId, Long restId) {
		try {
			int result = getSession().createQuery(
					"DELETE FROM Favorite f WHERE f.member.memberId = :memberId AND f.restaurant.restId = :restId")
					.setParameter("memberId", memberId).setParameter("restId", restId).executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

}