package com.tia102g4.rest.to;

public class RestaurantReqTO {
	private Long restId;
	private String restName;
	private String description;
	private String location;
	private String phone;
	private String email;
	private String openDay;
	private String openTime1;
	private String closeTime1;
	private String openTime2;
	private String closeTime2;
	private String password;
	private String image;
	private Long restType;
	
	public RestaurantReqTO() {
	}
	public RestaurantReqTO(Long restId, String restName, String description, String location, String phone, String email,
			String openDay, String openTime1, String closeTime1, String openTime2, String closeTime2, String password,
			String image, Long restType) {
		super();
		this.restId = restId;
		this.restName = restName;
		this.description = description;
		this.location = location;
		this.phone = phone;
		this.email = email;
		this.openDay = openDay;
		this.openTime1 = openTime1;
		this.closeTime1 = closeTime1;
		this.openTime2 = openTime2;
		this.closeTime2 = closeTime2;
		this.password = password;
		this.image = image;
		this.restType = restType;
	}
	public void setRestId(Long restId) {
		this.restId = restId;
	}
	public void setRestName(String restName) {
		this.restName = restName;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setOpenDay(String openDay) {
		this.openDay = openDay;
	}
	public void setOpenTime1(String openTime1) {
		this.openTime1 = openTime1;
	}
	public void setCloseTime1(String closeTime1) {
		this.closeTime1 = closeTime1;
	}
	public void setOpenTime2(String openTime2) {
		this.openTime2 = openTime2;
	}
	public void setCloseTime2(String closeTime2) {
		this.closeTime2 = closeTime2;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setRestType(Long restType) {
		this.restType = restType;
	}
	public Long getRestId() {
		return restId;
	}
	public String getRestName() {
		return restName;
	}
	public String getDescription() {
		return description;
	}
	public String getLocation() {
		return location;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public String getOpenDay() {
		return openDay;
	}
	public String getOpenTime1() {
		return openTime1;
	}
	public String getCloseTime1() {
		return closeTime1;
	}
	public String getOpenTime2() {
		return openTime2;
	}
	public String getCloseTime2() {
		return closeTime2;
	}
	public String getPassword() {
		return password;
	}
	public String getImage() {
		return image;
	}
	public Long getRestType() {
		return restType;
	}
}
