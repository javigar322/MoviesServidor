package com.api.movies.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.movies.entities.Movie;
import com.api.movies.services.MovieService;

/**
 * Controlador para las operaciones relacionadas con películas.
 */
@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

	// Inicialización del logger para el controlador de películas
	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private MovieService movieService;

	/**
	 * Obtiene todas las películas.
	 * 
	 * @return Lista de películas.
	 */
	@GetMapping
	public ResponseEntity<List<Movie>> getAllMovies() {
		try {
			List<Movie> movies = movieService.getAllMovies();
			logger.info("Returned {} movies.", movies.size());
			return new ResponseEntity<>(movies, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting all movies.", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Obtiene una película por su ID.
	 * 
	 * @param id ID de la película a obtener.
	 * @return La película correspondiente al ID proporcionado, o un mensaje de
	 *         error si no se encuentra.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable Long id) {
		try {
			Movie movie = movieService.getMovieById(id);
			if (movie != null) {
				logger.info("Returned movie with ID: {}", id);
				return new ResponseEntity<>(movie, HttpStatus.OK);
			} else {
				logger.warn("Movie with ID {} not found.", id);
				return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error("Error while getting movie with ID: {}", id, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Crea una nueva película.
	 * 
	 * @param movie La película a crear.
	 * @return La película creada.
	 */
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> createMovie(@RequestBody Movie movie) {
		try {
			Movie createdMovie = movieService.createMovie(movie);
			logger.info("Created movie with ID: {}", createdMovie.getId());
			return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Error while creating a new movie.", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Actualiza una película existente.
	 * 
	 * @param id    ID de la película a actualizar.
	 * @param movie La información actualizada de la película.
	 * @return La película actualizada, o null si no se encuentra la película con el
	 *         ID proporcionado.
	 */
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
		try {
			Movie updatedMovie = movieService.updateMovie(id, movie);
			if (updatedMovie != null) {
				logger.info("Updated movie with ID: {}", id);
				return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
			} else {
				logger.warn("Movie with ID {} not found for update.", id);
				return new ResponseEntity<>("Movie not found for update", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error("Error while updating movie with ID: {}", id, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Elimina una película por su ID.
	 * 
	 * @param id ID de la película a eliminar.
	 * @return true si la película se elimina correctamente, false si no se
	 *         encuentra la película con el ID proporcionado.
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
		try {
			boolean deleted = movieService.deleteMovie(id);
			if (deleted) {
				logger.info("Deleted movie with ID: {}", id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				logger.warn("Movie with ID {} not found for deletion.", id);
				return new ResponseEntity<>("Movie not found for deletion", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error("Error while deleting movie with ID: {}", id, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Busca películas por su nombre o género.
	 * 
	 * @param keyword Palabra clave para la búsqueda (nombre o género).
	 * @return Lista de películas que coinciden con la palabra clave.
	 */
	@GetMapping("/search")
	public ResponseEntity<List<Movie>> searchMovies(@RequestParam String keyword) {
		try {
			List<Movie> searchResults = movieService.searchMovies(keyword);
			logger.info("Returned {} search results for keyword: {}", searchResults.size(), keyword);
			return new ResponseEntity<>(searchResults, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while searching movies.", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
