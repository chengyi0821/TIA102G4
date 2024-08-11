package com.tia102g4.blacklist.service;

import java.util.List;
import java.util.Map;

import com.tia102g4.blacklist.model.BlackList;
import com.tia102g4.myorder.model.MyOrder;

public interface BlackListService {

	BlackList addBlackList(BlackList blacklist);
	
	void deleteBlackList(Long blackListId);
	
	List<BlackList> getAllBlackList();
	
	List<BlackList> getBlackListByCompositeQuery(Map<String, String[]> map);
	
	 boolean isMemberInBlackList(Long memberId);
}
