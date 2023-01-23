package com.Project.Movie.Movie;

import com.Project.Movie.users.dtos.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public List<MovieEntity> getAllMovies(@AuthenticationPrincipal UserResponseDto user){
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieEntity> getMovieById(@AuthenticationPrincipal UserResponseDto user, @PathVariable int id) {
        try {
            MovieEntity movie = movieService.getMovieById(id);
            return ResponseEntity.ok(movie);
        } catch (MovieNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/all")
    public List<MovieEntity> getAllMoviesByUserId(@PathVariable int id){
        return movieService.getAllMoviesByUserid(id);
    }
    @PostMapping
    public ResponseEntity<MovieEntity> createMovie(@RequestBody MovieEntity movie){
        try{
            return new ResponseEntity<>(movieService.createMovie(movie), HttpStatus.CREATED);
        }catch(RuntimeException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public MovieEntity updateMovie(@PathVariable int id, @RequestBody MovieEntity movie) throws MovieNotFoundException {
        return movieService.updateMovie(id, movie);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int id) throws MovieNotFoundException{
       movieService.deleteMovie(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
