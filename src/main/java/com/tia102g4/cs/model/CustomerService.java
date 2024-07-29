package com.tia102g4.cs.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.tia102g4.admin.model.Admin;
import com.tia102g4.member.model.Member;
import com.tia102g4.rest.model.Restaurant;

@Entity
@Table(name = "customer_service")
public class CustomerService {
	@Id
	@Column(name = "cs_id", updatable = false)
	@Expose
	private Long csId; 					// 客服ID
	
	@Column(name = "feedback_type")
	@Expose
	private Integer feedbackType; 		// 意見反應類別
	
	@Column(name = "feedback_content")
	@Expose
	private String feedbackContent;		// 意見反應內容
	
	@Column(name = "feedback_time")
	@Expose
	private Timestamp feedbackTime;		// 意見反應時間
	
	@Column(name = "reply_heading")
	@Expose
	private Integer replyHeading;		// 回覆主旨
	
	@Column(name = "reply_content")
	@Expose
	private String replyContent;		// 回覆內容
	
	@Column(name = "reply_time")
	@Expose
	private Timestamp replyTime;		// 回覆時間
	
	@Column(name = "reply_status")
	@Expose
	private Boolean replyStatus;		// 回覆狀態
	
	@Column(name = "deleted_rest")
	@Expose
	private Boolean deletedRest;		// 餐廳刪除狀態
	
	@Column(name = "deleted_member")
	@Expose
	private Boolean deletedMember;		// 會員刪除狀態
	
	@Column(name = "deleted_admin")
	@Expose
	private Boolean deletedAdmin;		// 後台人員刪除狀態
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rest_id", referencedColumnName = "rest_id")
	@Expose
	private Restaurant restaurant;		// 餐廳ID
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", referencedColumnName = "member_id")
	@Expose
	private Member member;				// 會員ID
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id", referencedColumnName = "admin_id")
	@Expose
	private Admin admin;				// 後台人員ID
	
	public CustomerService() {
		super();
	}

	public CustomerService(Long csId, Integer feedbackType, String feedbackContent, Timestamp feedbackTime,
			Integer replyHeading, String replyContent, Timestamp replyTime, Boolean replyStatus, Boolean deletedRest,
			Boolean deletedMember, Boolean deletedAdmin) {
		super();
		this.csId = csId;
		this.feedbackType = feedbackType;
		this.feedbackContent = feedbackContent;
		this.feedbackTime = feedbackTime;
		this.replyHeading = replyHeading;
		this.replyContent = replyContent;
		this.replyTime = replyTime;
		this.replyStatus = replyStatus;
		this.deletedRest = deletedRest;
		this.deletedMember = deletedMember;
		this.deletedAdmin = deletedAdmin;
	}

	public Long getCsId() {
		return csId;
	}

	public void setCsId(Long csId) {
		this.csId = csId;
	}

	public Integer getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(Integer feedbackType) {
		this.feedbackType = feedbackType;
	}

	public String getFeedbackContent() {
		return feedbackContent;
	}

	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}

	public Timestamp getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(Timestamp feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public Integer getReplyHeading() {
		return replyHeading;
	}

	public void setReplyHeading(Integer replyHeading) {
		this.replyHeading = replyHeading;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Timestamp getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Timestamp replyTime) {
		this.replyTime = replyTime;
	}

	public Boolean getReplyStatus() {
		return replyStatus;
	}

	public void setReplyStatus(Boolean replyStatus) {
		this.replyStatus = replyStatus;
	}

	public Boolean getDeletedRest() {
		return deletedRest;
	}

	public void setDeletedRest(Boolean deletedRest) {
		this.deletedRest = deletedRest;
	}

	public Boolean getDeletedMember() {
		return deletedMember;
	}

	public void setDeletedMember(Boolean deletedMember) {
		this.deletedMember = deletedMember;
	}

	public Boolean getDeletedAdmin() {
		return deletedAdmin;
	}

	public void setDeletedAdmin(Boolean deletedAdmin) {
		this.deletedAdmin = deletedAdmin;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "CustomerService [csId=" + csId + ", feedbackType=" + feedbackType + ", feedbackContent="
				+ feedbackContent + ", feedbackTime=" + feedbackTime + ", replyHeading=" + replyHeading
				+ ", replyContent=" + replyContent + ", replyTime=" + replyTime + ", replyStatus=" + replyStatus
				+ ", deletedRest=" + deletedRest + ", deletedMember=" + deletedMember + ", deletedAdmin=" + deletedAdmin
				+ "]";
	}
}
