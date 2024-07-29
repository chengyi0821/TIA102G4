package com.tia102g4.favorite.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//import com.tia102g4.member.model.Member;
//import com.tia102g4.rest.model.Restaurant;

@Entity
@Table(name = "favorite_restaurant")
public class Favorite {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fav_rest_id",updatable = false)
	private Long favoriteId;
	
	@Column(name="add_time", nullable = false)
	private Timestamp addTime;
	
//	@ManyToOne
//	@JoinColumn(name = "rest_id", nullable = false)
//	 private Restaurant restaurant;
//	
//	@ManyToOne
//	@JoinColumn(name = "member_id", nullable = false)
//	private Member member;

	public Favorite() {
		super();
	}

//	public Favorite(Long favoriteId, Timestamp addTime, Restaurant restaurant, Member member) {
//		super();
//		this.favoriteId = favoriteId;
//		this.addTime = addTime;
//		this.restaurant = restaurant;
//		this.member = member;
//	}

	public Long getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(Long favoriteId) {
		this.favoriteId = favoriteId;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

//	public Restaurant getRestaurant() {
//		return restaurant;
//	}
//
//	public void setRestaurant(Restaurant restaurant) {
//		this.restaurant = restaurant;
//	}
//
//	public Member getMember() {
//		return member;
//	}
//
//	public void setMember(Member member) {
//		this.member = member;
//	}
//	
	
	
}
