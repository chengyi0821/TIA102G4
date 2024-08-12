package com.tia102g4.cs.dao;

import java.util.List;
import java.util.Map;

import com.tia102g4.cs.model.CustomerService;

public interface RestFeedbackDAO {
	void insert(Integer feedbackType, String feedbackContent);
	
	void delete(Long csId, Boolean deletedRest);
	
	List<CustomerService> getByCompositeQuery(Map<String, String> map);
	
	List<CustomerService> getAll(int currentPage);
	
	long getTotal();
}
