package com.tia102g4.cs.service;

import java.util.List;
import java.util.Map;

import com.tia102g4.cs.model.CustomerService;
import com.tia102g4.cs.to.req.CSReqTo;

public interface CSService { 
	
	void create(CSReqTo reqTO);
	
	void update(CSReqTo reqTO);
	
	void delete(CSReqTo reqTO);
	
	List<CustomerService> getAllCS(int currentPage);
	
	int getPageTotal();
	
	List<CustomerService> getCSByCompositeQuery(Map<String, String[]> map);
}
