package com.tia102g4.cs.to.req;

import com.google.gson.annotations.Expose;

import common.CSFeedbackType;
import common.CSReplyHeading;

public class CSReqTO {
	@Expose
	private Long csId;
	@Expose
	private CSFeedbackType feedbackType;
	@Expose
	private String feedbackContent;
	@Expose
	private String feedbackTime;
	@Expose
	private CSReplyHeading replyHeading;
	@Expose
	private String replyContent;
	@Expose
	private String replyTime;
	@Expose
	private Boolean replyStatus;
	@Expose
	private Boolean deletedAdmin;
	@Expose
	private Boolean deletedMember;
	@Expose
	private Boolean deletedRest;
	@Expose
	private Long memberId;
	@Expose
	private String memberName;
	@Expose
	private Long restId;
	@Expose
	private String restName;
	@Expose
	private Long adminId;
	@Expose
	private String adminName;

	public CSReqTO() {
	}

	public CSReqTO(Long csId, CSFeedbackType feedbackType, String feedbackContent, String feedbackTime,
			CSReplyHeading replyHeading, String replyContent, String replyTime, Boolean replyStatus,
			Boolean deletedAdmin,Boolean deletedMember,Boolean deletedRest ,Long memberId, String memberName, Long restId, String restName, Long adminId,
			String adminName) {
		super();
		this.csId = csId;
		this.feedbackType = feedbackType;
		this.feedbackContent = feedbackContent;
		this.feedbackTime = feedbackTime;
		this.replyHeading = replyHeading;
		this.replyContent = replyContent;
		this.replyTime = replyTime;
		this.replyStatus = replyStatus;
		this.deletedAdmin = deletedAdmin;
		this.deletedMember = deletedMember;
		this.deletedRest = deletedRest;
		this.memberId = memberId;
		this.memberName = memberName;
		this.restId = restId;
		this.restName = restName;
		this.adminId = adminId;
		this.adminName = adminName;
	}

	public Long getCsId() {
		return csId;
	}

	public CSFeedbackType getFeedbackType() {
		return feedbackType;
	}

	public String getFeedbackContent() {
		return feedbackContent;
	}

	public String getFeedbackTime() {
		return feedbackTime;
	}

	public CSReplyHeading getReplyHeading() {
		return replyHeading;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public Boolean getReplyStatus() {
		return replyStatus;
	}

	public Boolean getDeletedAdmin() {
		return deletedAdmin;
	}

	public Boolean getDeletedMember() {
		return deletedMember;
	}

	public Boolean getDeletedRest() {
		return deletedRest;
	}

	public Long getMemberId() {
		return memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public Long getRestId() {
		return restId;
	}

	public String getRestName() {
		return restName;
	}

	public Long getAdminId() {
		return adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setCsId(Long csId) {
		this.csId = csId;
	}

	public void setFeedbackType(CSFeedbackType feedbackType) {
		this.feedbackType = feedbackType;
	}

	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}

	public void setFeedbackTime(String feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public void setReplyHeading(CSReplyHeading replyHeading) {
		this.replyHeading = replyHeading;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public void setReplyStatus(Boolean replyStatus) {
		this.replyStatus = replyStatus;
	}

	public void setDeletedAdmin(Boolean deletedAdmin) {
		this.deletedAdmin = deletedAdmin;
	}

	public void setDeletedMember(Boolean deletedMember) {
		this.deletedMember = deletedMember;
	}

	public void setDeletedRest(Boolean deletedRest) {
		this.deletedRest = deletedRest;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public void setRestId(Long restId) {
		this.restId = restId;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
}
