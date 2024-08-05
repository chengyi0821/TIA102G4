package com.tia102g4.cs.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.tia102g4.cs.model.CustomerService;
import com.tia102g4.cs.to.req.CSUpdateReqTO;

public interface CSDAO {
	
	void update(Long csId, Integer replyHeading, String replyContent,Timestamp replyTime ,Boolean replyStatus, Long adminId);
	
	void delete(Long csId, Boolean deletedAdmin);
	
	List<CustomerService> getByCompositeQuery(Map<String, String> map);
	
	List<CustomerService> getAll(int currentPage);
	
	long getTotal();
}
