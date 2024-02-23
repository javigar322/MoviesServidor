package com.api.movies.services;

import java.util.List;

import com.api.movies.entities.Comment;

public interface CommentService {

	List<Comment> getAllComments();

	Comment findByID(Long id);

	void addComments(Comment comment);

	void deleteComment(Long id);

}
