package com.Project.Movie.Movie;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(int id) {
        super("Movie with ID " + id + " not found");
    }
}
