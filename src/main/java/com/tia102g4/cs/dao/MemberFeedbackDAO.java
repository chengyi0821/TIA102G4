package com.tia102g4.cs.dao;

import java.util.List;
import java.util.Map;

import com.tia102g4.cs.model.CustomerService;

public interface MemberFeedbackDAO {
	void insert(Integer feedbackType, String feedbackContent, Long restId);

	void delete(Long csId, Boolean deletedMember);

	List<CustomerService> getByCompositeQuery(Map<String, String> map, Long restId);

	List<CustomerService> getAll(int currentPage, Long restId);

	long getTotal();
}
