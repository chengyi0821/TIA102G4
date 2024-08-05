package com.tia102g4.anno.to.req;

import common.AnnoType;

public class AnnoReqTO {

	private Long annoId;

	private String startDate;

	private String endDate;

	private String heading;

	private String content;

	private AnnoType type;

	private String image;

	private Boolean deleted;

	public AnnoReqTO() {
	}

	public AnnoReqTO(Long annoId, String startDate, String endDate, String heading, String content, AnnoType type, String image,
			Boolean deleted) {
		super();
		this.annoId = annoId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.heading = heading;
		this.content = content;
		this.type = type;
		this.image = image;
		this.deleted = deleted;
	}

	public Long getId() {
		return annoId;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
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

	public void setId(Long annoId) {
		this.annoId = annoId;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setType(AnnoType type) {
		this.type = type;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
