package com.tia102g4.anno.model;

import java.sql.Date;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "announcement")
public class Anno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "anno_id", updatable = false)
	private Long annoId;	//公告編號
	
	@NotNull(message = "起始日期不得為空")
	@Column(name = "start_date")
	private Date startDate;	//起始日期
	
	@NotNull(message = "結束日期不得為空")
	@Column(name = "end_date")
	private Date endDate;	//結束日期
	
	@NotBlank(message = "請填寫公告主旨")
	@Size(max = 50, message = "主旨不得超過50字")
	@Column(name = "heading")
	private String heading;	//主旨
	
	@NotBlank(message = "請填寫公告內容")
	@Size(max = 500, message = "內容不得超過500字")
	@Column(name = "content")
	private String content;	//內容
	
	@Column(name = "type")
	private Integer type;	//類型
	
	@NotEmpty(message="公告圖片: 請上傳照片")
	@Size(max = 2147483647, message = "圖片大小不得超過2GB")
	@Column(name = "image", columnDefinition = "LONGBLOB")
	private Byte[] image;	//圖片
	
	@Column(name = "deleted")
	private Boolean deleted; //刪除狀態


	public Anno() {
	}

	public Anno(Long annoId, Date startDate, Date endDate, String heading, String content, Integer type, Byte[] image,
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

	public Long getAnnoId() {
		return annoId;
	}

	public void setAnnoId(Long annoId) {
		this.annoId = annoId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public Boolean getDeleted() {
		return deleted;
	}
	
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Anno [annoId=" + annoId + ", startDate=" + startDate + ", endDate=" + endDate + ", heading=" + heading
				+ ", content=" + content + ", type=" + type + ", image=" + Arrays.toString(image) + ", deleted="
				+ deleted + "]";
	}
}
