package com.tia102g4.comment.dao;

import java.util.List;
import java.util.Map;

import com.tia102g4.comment.model.Comment;
//import com.tia102g4.restaurant.model.Restaurant;

public interface CommentDAO {
	
	void addComment(Comment entity);
	
    void updateComment(Comment entity);
        
    List<Comment> getAll();
    
    List<Comment> getByCompositeQuery(Map<String, String> map);
    
}
