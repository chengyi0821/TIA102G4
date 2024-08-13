package com.tia102g4.rest.model;

import java.io.InputStream;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "restaurant")
public class Restaurant {

	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rest_id")
	private Long restId; // 餐廳ID

	@ManyToOne
	@JoinColumn(name = "type_id", nullable = false)
	private Long typeId; // 餐廳類型

	@Column(name = "rest_name", nullable = false, unique = true)
	@Expose
	private String restName; // 餐廳名稱

	@Column(name = "description", nullable = false)
	private String description; // 餐廳描述

	@Column(name = "location", nullable = false)
	private String location; // 餐廳位置

	@Column(name = "phone", nullable = false, unique = true)
	private String phone; // 餐廳電話

	@Column(name = "regist_time", nullable = false)
	private Timestamp registTime; // 註冊時間

	@Column(name = "email", nullable = false, unique = true)
	private String email; // 餐廳信箱

	@Column(name = "open_day", nullable = false)
	private String openDay; // 營業日: 0.公休 1.營業

	@Column(name = "open_time1", nullable = false)
	private Time openTime1; // 營業時段1

	@Column(name = "close_time1", nullable = false)
	private Time closeTime1; // 打烊時間1

	@Column(name = "open_time2")
	private Time openTime2; // 營業時段2

	@Column(name = "close_time2")
	private Time closeTime2; // 打烊時間2

	@Column(name = "password", nullable = false)
	private String password; // 餐廳密碼

	@Column(name = "rating_pnum")
	private Integer ratingPnum; // 評分總人數

	@Column(name = "rating_star")
	private Integer ratingStar; // 評分總星星數

	@Column(name = "account", nullable = false, unique = true)
	private String account; // 帳號

	@Column(name = "sticker")
	private byte[] sticker; // 餐廳照片

	@Column(name = "acc_status", nullable = false)
	private Boolean accStatus; // 帳號狀態 0 使用中 1 已停用

	@Column(name = "reset_token")
	private String resetToken; // 重設密碼金鑰

	@Column(name = "token_Created_At")
	private Timestamp tokenCreatedAt; // 重設密碼金鑰創建時間

//	// 一對多關係：一個餐廳可以有多個評論
//	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
//	private Set<Comment> comments;
//
//	// 一對多關係：一個餐廳可以有多個訂單
//	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
//	private Set<MyOrder> myOrders;
//
//	// 一對多關係：一個餐廳可以有多個黑名單
//	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
//	private Set<BlackList> blackLists;
//
//	// 一對多關係：一個餐廳可以有多個客戶服務
//	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
//	private Set<CustomerService> customerServices;
//
//	// 一對多關係：一個餐廳可以被多次收藏
//	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
//	private Set<FavoriteRestaurant> favoriteRestaurants;
//
//	// 一對多關係：一個餐廳可以有多個活動
//	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
//	private Set<Event> events;
//
//	// 一對多關係：一個餐廳可以有多個投票
//	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
//	private Set<Vote> votes;
//
//	// 一對多關係：一個餐廳可以有多個餐廳消息
//	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
//	private Set<RestaurantNews> restaurantNews;
//
//	// 一對多關係：一個餐廳可以有多個餐廳菜單圖片
//	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
//	private Set<MenuImage> menuImages;

	public Restaurant() {
		super();
	}

	// Getters and Setters

	public Long getRestId() {
		return restId;
	}

	public void setRestId(Long restId) {
		this.restId = restId;
	}
	

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}


	public String getRestName() {
		return restName;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Timestamp getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Timestamp registTime) {
		this.registTime = registTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOpenDay() {
		return openDay;
	}

	public void setOpenDay(String openDay) {
		this.openDay = openDay;
	}

	public Time getOpenTime1() {
		return openTime1;
	}

	public void setOpenTime1(Time openTime1) {
		this.openTime1 = openTime1;
	}

	public Time getCloseTime1() {
		return closeTime1;
	}

	public void setCloseTime1(Time closeTime1) {
		this.closeTime1 = closeTime1;
	}

	public Time getOpenTime2() {
		return openTime2;
	}

	public void setOpenTime2(Time openTime2) {
		this.openTime2 = openTime2;
	}

	public Time getCloseTime2() {
		return closeTime2;
	}

	public void setCloseTime2(Time closeTime2) {
		this.closeTime2 = closeTime2;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRatingPnum() {
		return ratingPnum;
	}

	public void setRatingPnum(Integer ratingPnum) {
		this.ratingPnum = ratingPnum;
	}

	public Integer getRatingStar() {
		return ratingStar;
	}

	public void setRatingStar(Integer ratingStar) {
		this.ratingStar = ratingStar;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public byte[] getSticker() {
		return sticker;
	}

	public void setSticker(byte[] sticker) {
		this.sticker = sticker;
	}

	public Boolean getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(Boolean accStatus) {
		this.accStatus = accStatus;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public Timestamp getTokenCreatedAt() {
		return tokenCreatedAt;
	}
	
	public void setTokenCreatedAt(Timestamp tokenCreatedAt) {
		this.tokenCreatedAt = tokenCreatedAt;
	}

	@Override
	public String toString() {
		return "Restaurant [restId=" + restId + ", typeId=" + typeId + ", restName=" + restName + ", description="
				+ description + ", location=" + location + ", phone=" + phone + ", registTime=" + registTime
				+ ", email=" + email + ", openDay=" + openDay + ", openTime1=" + openTime1 + ", closeTime1="
				+ closeTime1 + ", openTime2=" + openTime2 + ", closeTime2=" + closeTime2 + ", password=" + password
				+ ", ratingPnum=" + ratingPnum + ", ratingStar=" + ratingStar + ", account=" + account + ", sticker="
				+ Arrays.toString(sticker) + ", accStatus=" + accStatus + ", resetToken=" + resetToken
				+ ", tokenCreatedAt=" + tokenCreatedAt + "]";
	}





//	public Set<Comment> getComments() {
//	return comments;
//}
//
//public void setComments(Set<Comment> comments) {
//	this.comments = comments;
//}
//
//public Set<MyOrder> getMyOrders() {
//	return myOrders;
//}
//
//public void setMyOrders(Set<MyOrder> myOrders) {
//	this.myOrders = myOrders;
//}
//
//public Set<BlackList> getBlackLists() {
//	return blackLists;
//}
//
//public void setBlackLists(Set<BlackList> blackLists) {
//	this.blackLists = blackLists;
//}
//
//public Set<CustomerService> getCustomerServices() {
//	return customerServices;
//}
//
//public void setCustomerServices(Set<CustomerService> customerServices) {
//	this.customerServices = customerServices;
//}
//
//public Set<FavoriteRestaurant> getFavoriteRestaurants() {
//	return favoriteRestaurants;
//}
//
//public void setFavoriteRestaurants(Set<FavoriteRestaurant> favoriteRestaurants) {
//	this.favoriteRestaurants = favoriteRestaurants;
//}
//
//public Set<Event> getEvents() {
//	return events;
//}
//
//public void setEvents(Set<Event> events) {
//	this.events = events;
//}
//
//public Set<Vote> getVotes() {
//	return votes;
//}
//
//public void setVotes(Set<Vote> votes) {
//	this.votes = votes;
//}
//
//public Set<RestaurantNews> getRestaurantNews() {
//	return restaurantNews;
//}
//
//public void setRestaurantNews(Set<RestaurantNews> restaurantNews) {
//	this.restaurantNews = restaurantNews;
//}
//
//public Set<MenuImage> getMenuImages() {
//	return menuImages;
//}
//
//public void setMenuImages(Set<MenuImage> menuImages) {
//	this.menuImages = menuImages;
//}

}
