package com.Project.Movie.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MovieEntity> getAllMovies() {
        return (List<MovieEntity>) movieRepository.findAll();
    }
    @Override
    public MovieEntity getMovieById(int id) {
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }
    @Override
    public MovieEntity createMovie(MovieEntity movie) {
        return movieRepository.save(movie);
    }
    @Override
    public MovieEntity updateMovie(int id, MovieEntity movie) {
        MovieEntity existingMovie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setReleaseYear(movie.getReleaseYear());
        existingMovie.setGenre(movie.getGenre());
        existingMovie.setRating(movie.getRating());
        return movieRepository.save(existingMovie);
    }

    public void deleteMovie(int id) {
        MovieEntity movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        movieRepository.deleteById(id);
    }
}