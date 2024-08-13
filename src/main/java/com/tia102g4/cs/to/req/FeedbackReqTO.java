package com.tia102g4.cs.to.req;

import com.google.gson.annotations.Expose;

import common.CSFeedbackType;

public class FeedbackReqTO {

	@Expose
	private CSFeedbackType feedbackType;
	@Expose
	private String feedbackContent;

	public FeedbackReqTO() {
	}

	public FeedbackReqTO(CSFeedbackType feedbackType, String feedbackContent) {
		super();
		this.feedbackType = feedbackType;
		this.feedbackContent = feedbackContent;
	}

	public CSFeedbackType getFeedbackType() {
		return feedbackType;
	}

	public String getFeedbackContent() {
		return feedbackContent;
	}

}
