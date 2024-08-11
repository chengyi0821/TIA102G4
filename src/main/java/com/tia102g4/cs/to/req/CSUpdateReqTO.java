package com.tia102g4.cs.to.req;

public class CSUpdateReqTO extends CSReqTO{
	
	private Long csId;
	
	public CSUpdateReqTO() {
		
	}
	public CSUpdateReqTO(Long csId) {
		super();
		this.csId = csId;
	}
	public Long getCsId() {
		return csId;
	}
}
