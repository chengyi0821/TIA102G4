package com.tia102g4.blacklist.model;


import java.sql.Date;
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
@Table(name= "black_list")
public class BlackList {
	@Id
	@Column(name = "black_id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long blackListId;
	
//	 @ManyToOne
//	 @JoinColumn(name = "member_id", nullable = false)
//	 private Member member;
//
//	 @ManyToOne
//	 @JoinColumn(name = "rest_id", nullable = false)
//	 private Restaurant restaurant;

	 @Column(name = "add_blist_time", nullable = false)
	 private Timestamp addBlistTime;

	 public BlackList() {
			super();
		}

//		public BlackList(Long blackListId, Member member, Restaurant restaurant, Timestamp addBlistTime) {
//			super();
//			this.blackListId = blackListId;
//			this.member = member;
//			this.restaurant = restaurant;
//			this.addBlistTime = addBlistTime;
//		}

		public Long getBlistId() {
			return blackListId;
		}

		public void setBlistId(Long blistId) {
			this.blackListId = blistId;
		}

//		public Member getMember() {
//			return member;
//		}
//
//		public void setMember(Member member) {
//			this.member = member;
//		}
//
//		public Restaurant getRestaurant() {
//			return restaurant;
//		}
//
//		public void setRest(Restaurant restaurant) {
//			this.restaurant = restaurant;
//		}

		public Timestamp getAddBlistTime() {
			return addBlistTime;
		}

		public void setAddBlistTime(Timestamp addBlistTime) {
			this.addBlistTime = addBlistTime;
		}

	    
}
