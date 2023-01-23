package com.Project.Movie.Movie;

import com.Project.Movie.users.UserEntity;
import com.Project.Movie.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    private final MovieRepository movieRepository;

    @Autowired
    private final UserRepository userRepository;

    public MovieServiceImpl(MovieRepository movieRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
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
    public MovieEntity createMovie(MovieEntity movie){
        UserEntity user = userRepository.findByUsername(movie.getUser().getUsername());
        if(user != null){
            movie.setUser(user);
            return movieRepository.save(movie);
        }else{
            throw new RuntimeException("User not found");
        }
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

    @Override
    public List<MovieEntity> getAllMoviesByUserid(int id) {
        MovieEntity movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        return movieRepository.findByUserId(id);
    }
}