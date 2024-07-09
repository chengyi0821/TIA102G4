package com.tia102g4.room.model;

public class RoomVO {
	private Integer status;
	private java.sql.Timestamp jointime;
	private java.sql.Timestamp esttime;
	
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public java.sql.Timestamp getJointime() {
		return jointime;
	}
	public void setJointime(java.sql.Timestamp jointime) {
		this.jointime = jointime;
	}
	public java.sql.Timestamp getEsttime() {
		return esttime;
	}
	public void setEsttime(java.sql.Timestamp esttime) {
		this.esttime = esttime;
	}
	
	
}
