package com.Project.Movie.Movie;
import java.util.*;
public interface MovieService {
    List<MovieEntity> getAllMovies();
    MovieEntity getMovieById(int id) throws MovieNotFoundException;
    MovieEntity createMovie(MovieEntity movie, int userID);
    MovieEntity updateMovie(int id, MovieEntity movie) throws MovieNotFoundException;
    void deleteMovie(int id) throws MovieNotFoundException;

    List<MovieEntity> findAllMoviesByUserid(int userId);
}
