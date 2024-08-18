package com.tia102g4.rest.to;

public class RestaurantUpdateReqTO extends RestaurantReqTO{
	private Long restId;

	public RestaurantUpdateReqTO() {
	}

	public RestaurantUpdateReqTO(Long restId) {
		super();
		this.restId = restId;
	}

	public Long getRestId() {
		return restId;
	}

	public void setRestId(Long restId) {
		this.restId = restId;
	}
}
