package com.tia102g4.vote.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vote")
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vote_id")
	private Long voteId;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "votrestno", referencedColumnName = "rest_id")
//	private Restaurant restaurant;
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "votevent", referencedColumnName = "event_id")
//	private Event event;

	@Column(name = "count")
	private Integer count;
	
	public Vote() {
		
	}
	
//  public Vote(Restaurant restaurant, Vote vote, Integer count) {
//	this.restaurant =restaurant;
//	this.vote = vote;
//	this.count = count;
// }
	public Long getVoteId() {
		return voteId;
	}

	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}

//	public Integer getRestId() {
//		return restId;
//	}
//
//	public void setRestId(Integer restId) {
//		this.restId = restId;
//	}

//	public Integer getEventId() {
//		return eventId;
//	}
//
//	public void setEventId(Integer eventId) {
//		this.eventId = eventId;
//	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Vote [voteId=" + voteId + ", count=" + count + "]";
	}

}
