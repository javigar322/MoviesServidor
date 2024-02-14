package com.api.movies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.movies.services.MovieService;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

	@Autowired
	MovieService movieService;
	
}
