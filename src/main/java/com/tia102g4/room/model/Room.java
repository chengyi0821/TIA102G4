package com.tia102g4.room.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="room")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="room_id")
	private Long   roomId;
	
//	@ManyToOne
//	@JoinColumn(name="roommbrno", referencedColumnName="room_id")
//	private Member member;
	
//  @ManyToOne	
//	@JoinColumn(name="roomeventno", referencedColumnName="room_id")
//    private Event event;
	
	@Column(name="est_time")
	private Timestamp estTime;
	
	@Column(name="join_time")
	private Timestamp joinTime;
	
	@Column(name="status")
	private Boolean   status;
	
	public Room() {
		super();
	}
	
//	public Room(Long roomId, Member member, Event event, Timestamp estTime, Timestamp joinTime, Boolean status) {
//		super();
//		this.roomId = roomId;
//		this.estTime = estTime;
//		this.joinTime = joinTime;
//		this.status = status;
//	}


	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
//	public Member getMember() {
//	return member;
//  }
//
//  public void setMember(Member member) {
//	this.member = member;
//  }
	
//	public event getEvent() {
//	return event;
//  }
//
//  public void setEvent(Event event) {
//	this.event = event;
//  }

	public Timestamp getEstTime() {
		return estTime;
	}

	public void setEstTime(Timestamp estTime) {
		this.estTime = estTime;
	}

	public Timestamp getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Timestamp joinTime) {
		this.joinTime = joinTime;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Room [estTime=" + estTime + ", joinTime=" + joinTime + ", status=" + status + "]";
	}	
	
	
	
}
