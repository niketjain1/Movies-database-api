package com.Project.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private final MovieService movieService;

    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieEntity> getAllMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieEntity> getMovieById(@PathVariable Long id) {
        try {
            MovieEntity movie = movieService.getMovieById(id);
            return ResponseEntity.ok(movie);
        } catch (MovieNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}")
    public MovieEntity createMovie(@RequestBody MovieEntity movie){
        return movieService.createMovie(movie);
    }

    @PutMapping("/{id}")
    public MovieEntity updateMovie(@PathVariable Long id, @RequestBody MovieEntity movie) throws MovieNotFoundException {
        return movieService.updateMovie(id, movie);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) throws MovieNotFoundException{
       movieService.deleteMovie(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
