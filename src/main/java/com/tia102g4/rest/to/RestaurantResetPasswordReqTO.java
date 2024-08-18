package com.tia102g4.rest.to;

public class RestaurantResetPasswordReqTO {
	private String email;
	
	public RestaurantResetPasswordReqTO() {
	}
	public RestaurantResetPasswordReqTO(String email) {
		super();
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
