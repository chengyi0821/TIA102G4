package com.tia102g4.restNews.to.req;

public class RestNewsDeleteReqTO {
	private Long newsId;
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
