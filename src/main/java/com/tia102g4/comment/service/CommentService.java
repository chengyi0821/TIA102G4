package com.tia102g4.comment.service;

import java.util.List;
import java.util.Map;

import com.tia102g4.comment.model.Comment;

public interface CommentService {

	Comment addComment(Comment entity);

	Comment updateComment(Comment entity);

	List<Comment> getCommentByCompositeQuery(Map<String, String[]> map);

}
