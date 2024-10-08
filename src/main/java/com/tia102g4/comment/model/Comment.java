package com.tia102g4.comment.model;

import javax.persistence.*;

import com.tia102g4.rest.model.Restaurant;

//import com.tia102g4.member.model.Member;
//import com.tia102g4.restaurant.model.Restaurant;

@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Long commentId;
	
//	@Column(name = "rest_id")
//	private Long restId;
	
	@Column(name = "member_id")
	private Long memberId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rest_id", referencedColumnName="rest_id")
	private Restaurant restaurant;
//	
//	@ManyToOne
//	@JoinColumn(name = "commbrno", referencedColumnName="member_id")
//	private Member member;

	@Column(name = "rating")
	private Integer rating;

	@Column(name = "content")
	private String content;

	// Constructors, getters, and setters
	public Comment() {
	}

//	public Comment(Restaurant restaurant, Member member, int rating, String content) {
//		this.restaurant = restaurant;
//		this.member = member;
//		this.rating = rating;
//		this.content = content;
//	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
//
//	public Member getMember() {
//		return member;
//	}
//
//	public void setMember(Member member) {
//		this.member = member;
//	}
	
//	public Long getRestId() {
//		return restId;
//	}
//	
//	public void setRestId(Long restId) {
//		this.restId = restId;
//	}
	
	public Long getMemberId() {
		return memberId;
	}
	
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
