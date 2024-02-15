package com.api.movies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.api.movies.controllers.MovieController;
import com.api.movies.entities.Movie;
import com.api.movies.services.MovieService;

@ExtendWith(MockitoExtension.class)
class MovieControllerTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    @Test
    void getAllMovies_shouldReturnListOfMovies() {
        // Arrange
        List<Movie> mockMovies = Arrays.asList(new Movie(), new Movie());
        when(movieService.getAllMovies()).thenReturn(mockMovies);

        // Act
        ResponseEntity<List<Movie>> response = movieController.getAllMovies();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockMovies, response.getBody());
    }

    @Test
    void getMovieById_shouldReturnMovie() {
        // Arrange
        long movieId = 1L;
        Movie mockMovie = new Movie();
        when(movieService.getMovieById(movieId)).thenReturn(mockMovie);

        // Act
        ResponseEntity<?> response = movieController.getMovieById(movieId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockMovie, response.getBody());
    }

    // Similar tests for createMovie, updateMovie, deleteMovie, and searchMovies...

    @Test
    void createMovie_shouldReturnCreatedMovie() {
        // Arrange
        Movie mockMovie = new Movie();
        when(movieService.createMovie(mockMovie)).thenReturn(mockMovie);

        // Act
        ResponseEntity<?> response = movieController.createMovie(mockMovie);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockMovie, response.getBody());
    }

    @Test
    void updateMovie_shouldReturnUpdatedMovie() {
        // Arrange
        long movieId = 1L;
        Movie mockMovie = new Movie();
        when(movieService.updateMovie(eq(movieId), any(Movie.class))).thenReturn(mockMovie);

        // Act
        ResponseEntity<?> response = movieController.updateMovie(movieId, mockMovie);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockMovie, response.getBody());
    }

    @Test
    void deleteMovie_shouldReturnNoContent() {
        // Arrange
        long movieId = 1L;
        when(movieService.deleteMovie(movieId)).thenReturn(true);

        // Act
        ResponseEntity<?> response = movieController.deleteMovie(movieId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void searchMovies_shouldReturnListOfMovies() {
        // Arrange
        String keyword = "action";
        List<Movie> mockSearchResults = Arrays.asList(new Movie(), new Movie());
        when(movieService.searchMovies(keyword)).thenReturn(mockSearchResults);

        // Act
        ResponseEntity<List<Movie>> response = movieController.searchMovies(keyword);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockSearchResults, response.getBody());
    }
}
