package com.tia102g4.blacklist.service;

import java.util.List;
import java.util.Map;

import com.tia102g4.blacklist.model.BlackList;
import com.tia102g4.myorder.model.MyOrder;

public interface BlackListService {

	BlackList addBlackList(BlackList blacklist);
	
	void deleteBlackList(Long blackListId);
	
	List<BlackList> getAllBlackList(Long restId);
	
	List<BlackList> getBlackListByCompositeQuery(Map<String, String[]> map, Long restId);
	
	 boolean isMemberInBlackList(Long memberId, Long restId);
}
