//package com.tia102g4.myorder.model;
//
//import java.sql.Date;
//import java.sql.Time;
//import java.sql.Timestamp;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import com.tia102g4.event.model.Event;
//import com.tia102g4.member.model.Member;
//import com.tia102g4.rest.model.Rest;
//
//@Entity
//@Table(name = "myorder")
//public class MyOrder {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "order_id", updatable = false)
//	private Long orderId;
//	
//	@Column(name = "name")
//	private String memberName;
//	
//	@Column(name = "phone")
//	private String phone;
//	
//	@Column(name = "order_date")
//	private Timestamp orderDate;
//	LocalTime currentTime = LocalTime.now().withNano(0);
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//	
//	@Column(name = "order_status")
//	private Integer orderStatus;
//	
//	@Column(name = "reser_date")
//	private Date reserDate;
//	
//	@Column(name = "reser_time")
//	private Time reserTime;
//
//	@Column(name = "reser_people_number")
//	private Integer reserPeopleNumber;
//	
//	@Column(name = "reser_note")
//	private String reserNote;
//	
//	@ManyToOne
//	@JoinColumn(name = "rest_id",referencedColumnName = "rest_id")
//	private Rest rest;
//	
//	@ManyToOne
//	@JoinColumn(name = "member_id",referencedColumnName = "member_id")
//	private Member member;
//	
//	@ManyToOne
//	@JoinColumn(name = "event_id",referencedColumnName = "event_id")
//	private Event event;
//	
//	public MyOrder() {
//	}
//
//
//	public MyOrder(Long orderId, String memberName, String phone, Timestamp orderDate, Integer orderStatus,
//			Date reserDate, Time reserTime, Integer reserPeopleNumber, String reserNote, Rest rest, Member member,
//			Event event) {
//		super();
//		this.orderId = orderId;
//		this.memberName = memberName;
//		this.phone = phone;
//		this.orderDate = orderDate;
//		this.orderStatus = orderStatus;
//		this.reserDate = reserDate;
//		this.reserTime = reserTime;
//		this.reserPeopleNumber = reserPeopleNumber;
//		this.reserNote = reserNote;
//		this.rest = rest;
//		this.member = member;
//		this.event = event;
//	}
//
//
//
//
//
//
//	public Long getOrderId() {
//		return orderId;
//	}
//
//	public void setOrderId(Long orderId) {
//		this.orderId = orderId;
//	}
//
//	public String getMemberName() {
//		return memberName;
//	}
//
//	public void setMemberName(String memberName) {
//		this.memberName = memberName;
//	}
//
//	public String getPhone() {
//		return phone;
//	}
//
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//
//	public Timestamp getOrderDate() {
//		return orderDate;
//	}
//
//	public void setOrderDate(Timestamp orderDate) {
//		this.orderDate = orderDate;
//	}
//
//	public Integer getOrderStatus() {
//		return orderStatus;
//	}
//
//	public void setOrderStatus(Integer orderStatus) {
//		this.orderStatus = orderStatus;
//	}
//
//	public Date getReserDate() {
//		return reserDate;
//	}
//
//	public void setReserDate(Date reserDate) {
//		this.reserDate = reserDate;
//	}
//
//	public Time getReserTime() {
//		return reserTime;
//	}
//
//	public void setReserTime(Time reserTime) {
//		this.reserTime = reserTime;
//	}
//
//	public Integer getReserPeopleNumber() {
//		return reserPeopleNumber;
//	}
//
//	public void setReserPeopleNumber(Integer reserPeopleNumber) {
//		this.reserPeopleNumber = reserPeopleNumber;
//	}
//
//	public String getReserNote() {
//		return reserNote;
//	}
//
//	public void setReserNote(String reserNote) {
//		this.reserNote = reserNote;
//	}
//
//	public Rest getRest() {
//		return rest;
//	}
//
//	public void setRest(Rest rest) {
//		this.rest = rest;
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
//	public Event getEvent() {
//		return event;
//	}
//
//	public void setEvent(Event event) {
//		this.event = event;
//	}
//
//	
//	
//	
//}
//
//
package com.tia102g4.myorder.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//import com.tia102g4.event.model.Event;
//import com.tia102g4.member.model.Member;
//import com.tia102g4.rest.model.Restaurant;
@Entity
@Table(name = "myorder")
public class MyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", updatable = false)
    private Long orderId;

    @Column(name = "name",nullable = false)
    private String memberName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "order_date", nullable = false)
    private Timestamp orderDate;

    @Column(name = "order_status", nullable = false)
    private String orderStatus;

    @Column(name = "reser_date", nullable = false)
    private Date reserDate;

    @Column(name = "reser_time", nullable = false)
    private Time reserTime;

    @Column(name = "reser_people_number", nullable = false)
    private Integer reserPeopleNumber;

    @Column(name = "reser_note")
    private String reserNote;

//    @ManyToOne
//    @JoinColumn(name = "rest_id", nullable = false) 
//    private Restaurant restaurant;
//
//    @ManyToOne
//    @JoinColumn(name = "member_id", nullable = false)
//    private Member member;
//
//    @ManyToOne
//    @JoinColumn(name = "event_id", nullable = false)
//    private Event event;
    
  
    public MyOrder() {
    }

//    public MyOrder(Long orderId, String memberName, String phone, Timestamp orderDate, String orderStatus,
//                   Date reserDate, Time reserTime, Integer reserPeopleNumber, String reserNote, Restaurant restaurant, Member member,
//                   Event event) {
//        super();
//        this.orderId = orderId;
//        this.memberName = memberName;
//        this.phone = phone;
//        this.orderDate = orderDate;
//        this.orderStatus = orderStatus;
//        this.reserDate = reserDate;
//        this.reserTime = reserTime;
//        this.reserPeopleNumber = reserPeopleNumber;
//        this.reserNote = reserNote;
//        this.restaurant = restaurant;
//        this.member = member;
//        this.event = event;
//    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getReserDate() {
        return reserDate;
    }

    public void setReserDate(Date reserDate) {
        this.reserDate = reserDate;
    }

    public Time getReserTime() {
        return reserTime;
    }

    public void setReserTime(Time reserTime) {
        this.reserTime = reserTime;
    }

    public Integer getReserPeopleNumber() {
        return reserPeopleNumber;
    }

    public void setReserPeopleNumber(Integer reserPeopleNumber) {
        this.reserPeopleNumber = reserPeopleNumber;
    }

    public String getReserNote() {
        return reserNote;
    }

    public void setReserNote(String reserNote) {
        this.reserNote = reserNote;
    }

//    public Restaurant getRestaurant() {
//        return restaurant;
//    }
//
//    public void setRestaurant(Restaurant restaurant) {
//        this.restaurant = restaurant;
//    }
//
//    public Member getMember() {
//        return member;
//    }
//
//    public void setMember(Member member) {
//        this.member = member;
//    }
//
//    public Event getEvent() {
//        return event;
//    }
//
//    public void setEvent(Event event) {
//        this.event = event;
//    }
    
   
}

