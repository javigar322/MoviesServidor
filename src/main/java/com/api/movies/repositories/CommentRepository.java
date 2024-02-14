package com.api.movies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.movies.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
