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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.gson.annotations.Expose;
import com.tia102g4.rest.model.Restaurant;


@Entity
@Table(name = "restaurant_news")
public class RestaurantNews {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "news_id", updatable = false)
	@Expose
	private Long newsId;	//消息編號
	
	@Column(name = "type")
	@Expose
	private Integer type;	//消息類別 1.公告 2.廣告
	
	@NotNull(message = "起始日期不得為空")
	@Column(name = "start_date")
	@Expose
	private Date startDate; //開始日期
	
	@NotNull(message = "結束日期不得為空")
	@Column(name = "end_date")
	@Expose
	private Date endDate;	//結束日期
	
	@NotBlank(message = "請填寫公告/廣告主旨")
	@Size(max = 50, message = "主旨不得超過50字")
	@Column(name = "heading")
	@Expose
	private String heading;	//主旨
	
	@NotBlank(message = "請填寫公告/廣告內容")
	@Size(max = 500, message = "內容不得超過500字")
	@Column(name = "content")
	@Expose
	private String content;	//內容
	
	@NotEmpty(message="公告圖片: 請上傳照片")
	@Size(max = 2147483647, message = "圖片大小不得超過2GB")
	@Column(name = "image", columnDefinition = "LONGBLOB")
	@Expose
	private Byte[] image;	//圖片
	
	@Column(name = "deleted")
	@Expose
	private Boolean deleted;//刪除狀態
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rest_id", referencedColumnName = "rest_id")
	@Expose
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
