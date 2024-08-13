package com.tia102g4.vote.service;

import java.util.List;
import java.util.Map;

import com.tia102g4.vote.model.Vote;

public interface VoteService {
	
	Vote addVote(Vote entity);
	
	Vote updateVote(Vote entity);
	
	List<Vote> getVoteByCompositeQuery(Map<String, String[]> map);
	

}
