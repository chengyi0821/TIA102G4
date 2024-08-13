package com.tia102g4.cs.service;

import java.util.List;
import java.util.Map;

import com.tia102g4.cs.to.req.CSReqTO;
import com.tia102g4.cs.to.req.FeedbackReqTO;

public interface FeedbackService {
	void insert(FeedbackReqTO reqTO);

	void delete(CSReqTO reqTO);

	List<CSReqTO> getAllCS(int currentPage);

	int getPageTotal();

	List<CSReqTO> getCSByCompositeQuery(Map<String, String[]> map);
}
