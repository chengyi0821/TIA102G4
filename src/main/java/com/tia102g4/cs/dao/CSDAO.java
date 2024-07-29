package com.tia102g4.cs.dao;

import java.util.List;
import java.util.Map;

import com.tia102g4.cs.model.CustomerService;

public interface CSDAO {
	
	int insert(CustomerService entity);
	
	int update(CustomerService entity);
	
	int delete(Integer id);
	
	List<CustomerService> getAll();
	
	List<CustomerService> getByCompositeQuery(Map<String, String> map);
	
	List<CustomerService> getAll(int currentPage);
	
	long getTotal();
}
