package com.tia102g4.cs.to.req;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.google.gson.annotations.Expose;

import common.CSFeedbackType;

public class FeedbackReqTO {

	@Expose
	private CSFeedbackType feedbackType;
	@Expose
	@NotBlank(message = "請填寫內容")
	@Size(max = 500, message = "內容不得超過500字")
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
