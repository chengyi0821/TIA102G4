package com.tia102g4.cs.to.req;

import java.sql.Timestamp;

import common.CSFeedbackType;
import common.CSReplyHeading;

public class CSReqTo {
	
	private CSFeedbackType feedbackType;
	
	private String feedbackContent;
	
	private Timestamp feedbackTime;
	
	private CSReplyHeading replyHeading;
	
	private String replyContent;
	
	private Timestamp replyTime;
	
	private Boolean replyStatus;
	
	private Boolean deletedAdmin;
	
	private String restName;

	public CSReqTo() {
	}

	public CSReqTo(CSFeedbackType feedbackType, String feedbackContent, Timestamp feedbackTime,
			CSReplyHeading replyHeading, String replyContent, Timestamp replyTime, Boolean replyStatus, Boolean deletedAdmin,
			String restName) {
		super();
		this.feedbackType = feedbackType;
		this.feedbackContent = feedbackContent;
		this.feedbackTime = feedbackTime;
		this.replyHeading = replyHeading;
		this.replyContent = replyContent;
		this.replyTime = replyTime;
		this.replyStatus = replyStatus;
		this.deletedAdmin = deletedAdmin;
		this.restName = restName;
	}

	public CSFeedbackType getFeedbackType() {
		return feedbackType;
	}

	public String getFeedbackContent() {
		return feedbackContent;
	}

	public Timestamp getFeedbackTime() {
		return feedbackTime;
	}

	public CSReplyHeading getReplyHeading() {
		return replyHeading;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public Timestamp getReplyTime() {
		return replyTime;
	}

	public Boolean getReplyStatus() {
		return replyStatus;
	}

	public Boolean getDeletedAdmin() {
		return deletedAdmin;
	}

	public String getRestName() {
		return restName;
	}

	public void setFeedbackType(CSFeedbackType feedbackType) {
		this.feedbackType = feedbackType;
	}

	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}

	public void setFeedbackTime(Timestamp feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public void setReplyHeading(CSReplyHeading replyHeading) {
		this.replyHeading = replyHeading;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public void setReplyTime(Timestamp replyTime) {
		this.replyTime = replyTime;
	}

	public void setReplyStatus(Boolean replyStatus) {
		this.replyStatus = replyStatus;
	}

	public void setDeletedAdmin(Boolean deletedAdmin) {
		this.deletedAdmin = deletedAdmin;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}
	
	
}
