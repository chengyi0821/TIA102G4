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
	public void addComment(Comment entity) {
		try {
			dao.addComment(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateComment(Comment entity) {
		dao.updateComment(entity);
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
