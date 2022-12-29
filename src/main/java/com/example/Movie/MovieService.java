package com.example.Movie;
import java.util.*;
public interface MovieService {
    List<MovieEntity> getAllMovies();
    MovieEntity getMovieById(Long id) throws MovieNotFoundException;
    MovieEntity createMovie(MovieEntity movie);
    MovieEntity updateMovie(Long id, MovieEntity movie) throws MovieNotFoundException;
    void deleteMovie(Long id) throws MovieNotFoundException;
}
