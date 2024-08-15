package com.tia102g4.restNews.to.req;

import com.google.gson.annotations.Expose;

public class RestNewsDeleteReqTO {
	@Expose
	private Long newsId;
	@Expose
	private Boolean deleted;
	
	public RestNewsDeleteReqTO() {
		super();
	}

	public RestNewsDeleteReqTO(Long newsId, Boolean deleted) {
		super();
		this.newsId = newsId;
		this.deleted = deleted;
	}

	public Long getNewsId() {
		return newsId;
	}

	public Boolean getDeleted() {
		return deleted;
	}
	
}
