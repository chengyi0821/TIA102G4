package com.tia102g4.blacklist.dao;

import java.util.List;
import java.util.Map;

import com.tia102g4.blacklist.model.BlackList;
import com.tia102g4.myorder.model.MyOrder;


public interface BlackListDAO {

	int insert(BlackList blacklist);
	
	int delete(Long blackListId);
	
	List<BlackList> getAll(Long restId);
	
	List<BlackList> getByCompositeQuery(Map<String, String> map, Long restId);
	
	boolean isMemberInBlackList(Long memberId, Long restId);
	
	
}
