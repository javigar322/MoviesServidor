package com.api.movies.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.movies.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long>{

	List<Movie> findByNameContainingIgnoreCaseOrGenreContainingIgnoreCase(String keyword, String keyword2);

}
