package com.tia102g4.favorite.service;

import java.sql.Timestamp;
import java.util.List;

import com.tia102g4.favorite.dao.FavoriteDAO;
import com.tia102g4.favorite.dao.FavoriteDAOImpl;
import com.tia102g4.favorite.model.Favorite;
import com.tia102g4.member.model.Member;
import com.tia102g4.rest.model.Restaurant;

public class FavoriteServiceImpl implements FavoriteService {
	private FavoriteDAO dao;

	public FavoriteServiceImpl() {
		dao = new FavoriteDAOImpl();
	}

	@Override
	public List<Favorite> getFav(Long memberId) {
		return dao.getFav(memberId);
	}

	@Override
	public boolean addFav(Favorite favorite) {
		if (isFavoriteExists(favorite.getMember().getMemberId(), favorite.getRestaurant().getRestId())) {
			return false;
		}

		try {
			dao.insertFav(favorite);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	  @Override
	    public boolean isFavoriteExists(Long memberId, Long restId) {
	        return dao.findByMemberIdAndRestId(memberId, restId) != null;
	    }
	 
	@Override
	public boolean deleteFav(Long memberId, Long restId) {
	    return dao.deleteFav(memberId, restId) > 0;
	}

}
