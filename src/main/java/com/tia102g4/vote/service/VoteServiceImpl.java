package com.tia102g4.vote.service;

import java.util.*;

import com.tia102g4.vote.dao.VoteDAO;
import com.tia102g4.vote.dao.VoteDAOImp1;
import com.tia102g4.vote.model.Vote;
public class VoteServiceImpl implements VoteService {
	
	private VoteDAO dao;
	
	public  VoteServiceImpl() {
		dao = new VoteDAOImp1();
	}

	@Override
	public Vote addVote(Vote entity) {
		dao.addVote(entity);
		return entity;
	}

	@Override
	public Vote updateVote(Vote entity) {
		dao.updateVote(entity);
		return entity;
	}

	@Override
	public List<Vote> getVoteByCompositeQuery(Map<String, String[]> map) {
		
		Map<String, String> query = new HashMap<>();
		Set<Map.Entry<String, String[]>> entry = map.entrySet();
		
		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			if("action".equals(key)) {
				continue;
			}
			String value = row.getValue()[0];
			if (value == null || value.isEmpty()) {
				continue;
			}
			query.put(key, value);
		}
		System.out.println(query);
		return dao.getByCompositeQuery(query);
	}
}
	