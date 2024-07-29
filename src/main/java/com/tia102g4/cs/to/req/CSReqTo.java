package com.tia102g4.cs.to.req;

import java.sql.Timestamp;

public class CSReqTo {
	
	private String feedbackType;
	
	private String feedbackContent;
	
	private Timestamp feedbackTime;
	
	private String replyHeading;
	
	private String replyContent;
	
	private Timestamp replyTime;
	
	private Boolean replyStatus;
	
	private Boolean deletedAdmin;
	
	private String restName;

	public CSReqTo() {
	}

	public CSReqTo(String feedbackType, String feedbackContent, Timestamp feedbackTime,
			String replyHeading, String replyContent, Timestamp replyTime, Boolean replyStatus, Boolean deletedAdmin,
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

	public String getFeedbackType() {
		return feedbackType;
	}

	public String getFeedbackContent() {
		return feedbackContent;
	}

	public Timestamp getFeedbackTime() {
		return feedbackTime;
	}

	public String getReplyHeading() {
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
}
