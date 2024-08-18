/**
 * 
 */
package com.tia102g4.cs.to.req;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.google.gson.annotations.Expose;

import common.CSReplyHeading;

public class CSInsertReqTO {
	@Expose
	private Long csId;
	@Expose
	private CSReplyHeading replyHeading;
	@Expose
	@NotBlank(message = "請填寫內容")
	@Size(max = 500, message = "內容不得超過500字")
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

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	
	

}
