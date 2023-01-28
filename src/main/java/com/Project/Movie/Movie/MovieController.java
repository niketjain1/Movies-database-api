package com.Project.Movie.Movie;

import com.Project.Movie.users.dtos.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class MovieController {

    @Autowired
    private final MovieService movieService;

    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }


    @GetMapping("/{userId}/movie")
    public List<MovieEntity> getAllMoviesByUserId(@AuthenticationPrincipal UserResponseDto user, @PathVariable("userId") int userId){
        System.out.println("hi");
        return movieService.findAllMoviesByUserid(userId);
    }

    @PostMapping("/{userId}/movie")
    public ResponseEntity<MovieEntity> createMovie(@AuthenticationPrincipal UserResponseDto user, @RequestBody MovieEntity movie, @PathVariable("userId") int userId){
        var savedMovie = movieService.createMovie(movie, userId);
        return ResponseEntity.created(URI.create("/users/" + savedMovie.getId() +"/movie/")).body(savedMovie);
    }

}
