package com.tia102g4.anno.to.req;

import java.util.Date;

import common.AnnoType;

public class AnnoReqTO {

	private Date startDate;

	private Date endDate;

	private String heading;

	private String content;

	private AnnoType type;

	private String image;
	
	private Boolean deleted;

	public AnnoReqTO() {
	}

	public AnnoReqTO(Date startDate, Date endDate, String heading, String content, AnnoType type, String image, Boolean deleted) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.heading = heading;
		this.content = content;
		this.type = type;
		this.image = image;
		this.deleted = deleted;
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

	public AnnoType getType() {
		return type;
	}

	public String getImage() {
		return image;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	
}
