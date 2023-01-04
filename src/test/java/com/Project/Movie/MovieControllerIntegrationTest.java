package com.Project.Movie;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.json.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
public class MovieControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MovieService movieService;


    @Test
    public void givenMovies_whenGetMovies_thenReturnJsonArray() throws Exception {
        LocalDate date = LocalDate.now();
//        MovieEntity it = new MovieEntity(21L, "It", date, "Horror", 8.4d);
//        MovieEntity bee = new MovieEntity(22L, "Bee", date, "Commedy", 9.1d);

        MovieEntity it = new MovieEntity("It", 8.4d);
        MovieEntity bee = new MovieEntity("Bee", 8.8d);

        List<MovieEntity> allMovies = Arrays.asList(it, bee);

        given(movieService.getAllMovies()).willReturn(allMovies);
        mvc.perform(get("/movies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(it.getId())))
                .andExpect(jsonPath("$[0].title", is(it.getTitle())))
                .andExpect(jsonPath("$[0].date", is(it.getReleaseDate())))
                .andExpect(jsonPath("$[0].genre", is(it.getGenre())))
                .andExpect(jsonPath("$[0].rating", is(it.getRating())));

        verify(movieService, VerificationModeFactory.times(1)).getAllMovies();
        reset(movieService);
    }
}
