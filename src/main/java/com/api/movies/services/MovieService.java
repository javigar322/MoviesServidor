package com.api.movies.services;

import java.util.List;

import com.api.movies.entities.Movie;

public interface MovieService {

	/**
	 * Obtiene todas las películas en la base de datos.
	 * @return Lista de todas las películas.
	 */
	List<Movie> getAllMovies();

	/**
	 * Obtiene una película por su ID.
	 * @param id ID de la película a obtener.
	 * @return La película correspondiente al ID proporcionado, o null si no se encuentra.
	 */
	Movie getMovieById(Long id);

	/**
	 * Crea una nueva película.
	 * @param movie La película a crear.
	 * @return La película creada.
	 */
	Movie createMovie(Movie movie);

	/**
	 * Actualiza una película existente.
	 * @param id   ID de la película a actualizar.
	 * @param movie La información actualizada de la película.
	 * @return La película actualizada, o null si no se encuentra la película con el ID proporcionado.
	 */
	Movie updateMovie(Long id, Movie movie);

	/**
	 * Elimina una película por su ID.
	 * @param id ID de la película a eliminar.
	 * @return true si la película se elimina correctamente, false si no se encuentra la película con el ID proporcionado.
	 */
	boolean deleteMovie(Long id);

	/**
	 * Busca películas por su nombre o género.
	 * @param keyword Palabra clave para la búsqueda (nombre o género).
	 * @return Lista de películas que coinciden con la palabra clave.
	 */
	List<Movie> searchMovies(String keyword);

}
