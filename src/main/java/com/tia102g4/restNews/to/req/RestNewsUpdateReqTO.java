package com.tia102g4.restNews.to.req;

public class RestNewsUpdateReqTO extends RestNewsReqTO{
	private Long newsId;

	public RestNewsUpdateReqTO() {
	}

	public RestNewsUpdateReqTO(Long newsId) {
		super();
		this.newsId = newsId;
	}

	public Long getNewsId() {
		return newsId;
	}

}
