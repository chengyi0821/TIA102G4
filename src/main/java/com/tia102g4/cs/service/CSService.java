package com.tia102g4.cs.service;

import java.util.List;
import java.util.Map;

import com.tia102g4.cs.to.req.CSReqTO;
import com.tia102g4.cs.to.req.CSUpdateReqTO;

public interface CSService { 
	
	void update(CSReqTO reqTO);
	
	void delete(CSReqTO reqTO);
	
	List<CSReqTO> getAllCS(int currentPage);
	
	int getPageTotal();
	
	List<CSReqTO> getCSByCompositeQuery(Map<String, String[]> map);
}
