package com.tia102g4.cs.service;

import java.util.List;
import java.util.Map;

import com.tia102g4.cs.to.req.CSReqTO;
import com.tia102g4.cs.to.req.FeedbackReqTO;

public interface FeedbackService {
	void insert(FeedbackReqTO reqTO, Long restId);

	void delete(CSReqTO reqTO);

	List<CSReqTO> getAllCS(int currentPage, Long restId);

	int getPageTotal();

	List<CSReqTO> getCSByCompositeQuery(Map<String, String[]> map, Long restId);
}
