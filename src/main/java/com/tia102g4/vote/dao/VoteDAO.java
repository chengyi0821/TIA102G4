package com.tia102g4.vote.dao;

import java.util.List;
import java.util.Map;

import com.tia102g4.vote.model.Vote;

public interface VoteDAO {

	int addVote(Vote entity);
	
	int updateVote(Vote entity);
	
	Vote getById(Integer id);
	
	List<Vote> getAll();
	
	List<Vote> getByCompositeQuery(Map<String, String> map);
	
	

	
}