/**
 * 
 */
package com.tia102g4.cs.to.req;

import com.google.gson.annotations.Expose;

import common.CSReplyHeading;

public class CSInsertReqTO {
	@Expose
	private Long csId;
	@Expose
	private CSReplyHeading replyHeading;
	@Expose
	private String replyContent;
	@Expose
	private Long adminId;

	public CSInsertReqTO() {
	}
	
	public CSInsertReqTO(Long csId, CSReplyHeading replyHeading, String replyContent, Long adminId) {
		super();
		this.csId = csId;
		this.replyHeading = replyHeading;
		this.replyContent = replyContent;
		this.adminId = adminId;
	}
	
	public Long getCsId() {
		return csId;
	}

	public CSReplyHeading getReplyHeading() {
		return replyHeading;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public Long getAdminId() {
		return adminId;
	}

}
