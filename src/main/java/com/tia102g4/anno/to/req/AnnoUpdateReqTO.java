package com.tia102g4.anno.to.req;

public class AnnoUpdateReqTO extends AnnoReqTO{
	private Long id;

	public AnnoUpdateReqTO() {
	}


	public AnnoUpdateReqTO(Long id) {
		super();
		this.id = id;
	}
	

	public Long getId() {
		return id;
	}
	
	
}
