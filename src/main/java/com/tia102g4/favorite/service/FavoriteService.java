package com.tia102g4.favorite.service;

import java.util.List;

import com.tia102g4.favorite.model.Favorite;

public interface FavoriteService {
	    List<Favorite> getFav(Long memberId);
	    boolean addFav(Favorite favorite);
	    boolean isFavoriteExists(Long memberId, Long restId);
	    boolean deleteFav(Long memberId, Long restId);
}
