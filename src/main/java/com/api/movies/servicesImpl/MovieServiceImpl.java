package com.api.movies.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.movies.entities.Movie;
import com.api.movies.repositories.MovieRepository;
import com.api.movies.services.MovieService;

/**
 * Implementación del servicio para operaciones CRUD y búsqueda de películas.
 */
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    /**
     * Obtiene todas las películas en la base de datos.
     * @return Lista de todas las películas.
     */
    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    /**
     * Obtiene una película por su ID.
     * @param id ID de la película a obtener.
     * @return La película correspondiente al ID proporcionado, o null si no se encuentra.
     */
    @Override
    public Movie getMovieById(Long id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        return optionalMovie.orElse(null);
    }

    /**
     * Crea una nueva película.
     * @param movie La película a crear.
     * @return La película creada.
     */
    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    /**
     * Actualiza una película existente.
     * @param id   ID de la película a actualizar.
     * @param movie La información actualizada de la película.
     * @return La película actualizada, o null si no se encuentra la película con el ID proporcionado.
     */
    @Override
    public Movie updateMovie(Long id, Movie movie) {
        if (movieRepository.existsById(id)) {
            movie.setId(id);
            return movieRepository.save(movie);
        } else {
            return null;
        }
    }

    /**
     * Elimina una película por su ID.
     * @param id ID de la película a eliminar.
     * @return true si la película se elimina correctamente, false si no se encuentra la película con el ID proporcionado.
     */
    @Override
    public boolean deleteMovie(Long id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Busca películas por su nombre o género.
     * @param keyword Palabra clave para la búsqueda (nombre o género).
     * @return Lista de películas que coinciden con la palabra clave.
     */
    @Override
    public List<Movie> searchMovies(String keyword) {
        return movieRepository.findByNameContainingIgnoreCaseOrGenreContainingIgnoreCase(keyword, keyword);
    }
}
