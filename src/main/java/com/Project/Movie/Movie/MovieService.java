package com.Project.Movie.Movie;
import java.util.*;
public interface MovieService {
    List<MovieEntity> getAllMovies();
    MovieEntity getMovieById(int id) throws MovieNotFoundException;
    MovieEntity createMovie(MovieEntity movie);
    MovieEntity updateMovie(int id, MovieEntity movie) throws MovieNotFoundException;
    void deleteMovie(int id) throws MovieNotFoundException;
}
