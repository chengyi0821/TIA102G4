package com.tia102g4.comment.service;

import java.util.*;

import com.tia102g4.comment.dao.CommentDAO;
import com.tia102g4.comment.dao.CommentDAOImpl;
import com.tia102g4.comment.model.Comment;

public class CommentServiceImpl implements CommentService {
	private CommentDAO dao;

	public CommentServiceImpl() {
		dao = new CommentDAOImpl();
	}

	@Override
	public Comment addComment(Comment entity) {
		dao.addComment(entity);
		return entity;
	}

	@Override
	public Comment updateComment(Comment entity) {
		dao.updateComment(entity);
		return entity;
	}

	@Override
	public List<Comment> getCommentByCompositeQuery(Map<String, String[]> map) {

		Map<String, String> query = new HashMap<>();
		Set<Map.Entry<String, String[]>> entry = map.entrySet();

		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			if ("action".equals(key)) {
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
