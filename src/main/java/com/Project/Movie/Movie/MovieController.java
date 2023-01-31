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
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private final MovieService movieService;

    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }


    @GetMapping("")
    public List<MovieEntity> getAllMoviesByUserId(@AuthenticationPrincipal UserResponseDto user){
        int userId = user.getId();
        return movieService.findAllMoviesByUserid(userId);
    }

    @PostMapping("")
    public ResponseEntity<MovieEntity> createMovie(@AuthenticationPrincipal UserResponseDto user, @RequestBody MovieEntity movie){
        int userId = user.getId();
        var savedMovie = movieService.createMovie(movie, userId);
        return ResponseEntity.created(URI.create("/users/" + savedMovie.getId() +"/movie/")).body(savedMovie);
    }

}
