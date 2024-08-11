package com.tia102g4.favorite.dao;

import java.util.List;

import com.tia102g4.favorite.model.Favorite;

public interface FavoriteDAO {

	List<Favorite> getFav(Long memberId);
    void insertFav(Favorite favorite);
    Favorite findByMemberIdAndRestId(Long memberId, Long restId);
	int deleteFav(Long memberId, Long favoriteId);
}
