package com.api.movies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.movies.services.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	CommentService commentService;
	
}
