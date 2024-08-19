package com.tia102g4.rest.to;

import com.google.gson.annotations.Expose;

public class RestaurantUpdateReqTO extends RestaurantReqTO{
	@Expose
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
