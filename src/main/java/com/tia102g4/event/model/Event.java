package com.tia102g4.event.model;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import javax.persistence.*;
//import com.tia102g4.member.model.*;
//import com.tia102g4.vote.model.Vote;

@Entity
@Table(name="event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id", updatable = false)
	private Long eventNo;
	
//	@ManyToOne 	//關聯到會員VO
//	@JoinColumn(name="eventmemberno", referencedColumnName = "member_id")
//	private Member member;
//	
//	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)    //關聯Vote
//	private Set<Vote> votes;
	
	@Column(name="name")
	private String name;
	
	@Column(name="date")
	private java.sql.Date date;
	
	@Column(name="time")
	private java.sql.Time time;
	
	@Column(name="info")
	private String info;
	
	@Column(name="seat", nullable = true)
	private Integer seat;
	
	@Column(name="max_seat")
	private Integer maxSeat;
	
	@Column(name="code")
	private String code;
	
	public Event() {
	}

//	public Event(Integer eventNo, Member member, Set<Vote> votes, String name, Date date, Time time, String info,
//			Integer seat, Integer maxSeat, String code) {
//		super();
//		this.eventNo = eventNo;
//		this.member = member;
//		this.votes = votes;
//		this.name = name;
//		this.date = date;
//		this.time = time;
//		this.info = info;
//		this.seat = seat;
//		this.maxSeat = maxSeat;
//		this.code = code;
//	}



	public Long getEventno() {
		return eventNo;
	}

	public void setEventno(Long eventno) {
		this.eventNo = eventno;
	}

//	public Member getMember() {
//		return member;
//	}
//
//	public void setMember(Integer memberid) {
//		this.member = member;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public java.sql.Time getTime() {
		return time;
	}

	public void setTime(java.sql.Time time) {
		this.time = time;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getSeat() {
		return seat;
	}

	public void setSeat(Integer seat) {
		this.seat = seat;
	}

	public Integer getMaxseat() {
		return maxSeat;
	}

	public void setMaxseat(Integer maxseat) {
		this.maxSeat = maxseat;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
//	@Override
//	public String toString() {
//		return "Event [ event_no :"+eventNo+", memeber_id :"+member+", name :"+ name 
//				+", date :"+date+", time :"+time+", info :"+ info +", seat :"+seat+", max_seat :"+maxSeat	
//				+", code :"+code+"]";
//	}
	
}
