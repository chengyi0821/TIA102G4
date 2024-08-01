package com.tia102g4.restNews.model;


import java.sql.Date;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tia102g4.rest.model.Restaurant;


@Entity
@Table(name = "restaurant_news")
public class RestaurantNews {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "news_id", updatable = false)
	private Long newsId;	//消息編號
	
	@Column(name = "type")
	private Integer type;	//消息類別 1.公告 2.廣告
	
	@Column(name = "start_date")
	private Date startDate; //開始日期
	
	@Column(name = "end_date")
	private Date endDate;	//結束日期
	
	@Column(name = "heading")
	private String heading;	//主旨
	
	@Column(name = "content")
	private String content;	//內容
	
	@Column(name = "image", columnDefinition = "LONGBLOB")
	private Byte[] image;	//圖片
	
	@Column(name = "deleted")
	private Boolean deleted;//刪除狀態
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rest_id", referencedColumnName = "rest_id")
	private Restaurant restaurant;

	public RestaurantNews() {
		super();
	}

	public RestaurantNews(Long newsId, Integer type, Date startDate, Date endDate, String heading, String content,
			Byte[] image, Boolean deleted) {
		super();
		this.newsId = newsId;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.heading = heading;
		this.content = content;
		this.image = image;
		this.deleted = deleted;
	}

	public Long getNewsId() {
		return newsId;
	}

	public Integer getType() {
		return type;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getHeading() {
		return heading;
	}

	public String getContent() {
		return content;
	}

	public Byte[] getImage() {
		return image;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public String toString() {
		return "RestaurantNews [newsId=" + newsId + ", type=" + type + ", startDate=" + startDate + ", endDate="
				+ endDate + ", heading=" + heading + ", content=" + content + ", image=" + Arrays.toString(image)
				+ ", deleted=" + deleted + "]";
	}
}
