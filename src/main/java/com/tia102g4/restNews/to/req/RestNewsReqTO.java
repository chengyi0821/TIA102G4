package com.tia102g4.restNews.to.req;

import common.RestNewsType;

public class RestNewsReqTO {
	private Long newsId;
	
	private RestNewsType type;
	
	private String startDate;
	
	private String endDate;
	
	private String heading;
	
	private String content;
	
	private String image;
	
	private Boolean deleted;
	
	private Long restId;
	
	private String restName;

	public RestNewsReqTO() {
	}

	public RestNewsReqTO(Long newsId, RestNewsType type, String startDate, String endDate, String heading, String content,
			String image, Boolean deleted,Long restId ,String restName) {
		super();
		this.newsId = newsId;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.heading = heading;
		this.content = content;
		this.image = image;
		this.deleted = deleted;
		this.restId = restId;
		this.restName = restName;
	}
	
	public Long getNewsId() {
		return newsId;
	}

	public RestNewsType getType() {
		return type;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getHeading() {
		return heading;
	}

	public String getContent() {
		return content;
	}

	public String getImage() {
		return image;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public Long getRestId() {
		return restId;
	}

	public String getRestName() {
		return restName;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public void setType(RestNewsType type) {
		this.type = type;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public void setRestId(Long restId) {
		this.restId = restId;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}

	@Override
	public String toString() {
		return "RestNewsReqTO [newsId=" + newsId + ", type=" + type + ", startDate=" + startDate + ", endDate="
				+ endDate + ", heading=" + heading + ", content=" + content + ", image=" + image + ", deleted="
				+ deleted + ", restId=" + restId + ", restName=" + restName + "]";
	}

}
