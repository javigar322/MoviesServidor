package com.api.movies.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.movies.entities.Comment;
import com.api.movies.repositories.CommentRepository;
import com.api.movies.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;
	
	@Override
	public List<Comment> getAllComments() {
		return commentRepository.findAll();
	}
	
	@Override
	public Comment findByID(Long id) {
		return commentRepository.findById(id).orElseThrow();
	}
	
	@Override
	public void addComments(Comment comment) {
		commentRepository.save(comment);
	}
	
	@Override
	public void deleteComment(Long id) {
		commentRepository.deleteById(id);
	}
	
}
