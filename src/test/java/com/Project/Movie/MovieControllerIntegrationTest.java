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
    public void whenPostMovie_thenCreateMovie() throws Exception{

        MovieEntity it = new MovieEntity(21, "It", 2019, "Horror", 8.4d);
        given(movieService.createMovie(Mockito.any())).willReturn(it);

        mvc.perform(post("/movies/01")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(it)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(it.getId())))
                .andExpect(jsonPath("$.title", is(it.getTitle())))
                .andExpect(jsonPath("$.releaseYear", is(it.getReleaseYear())))
                .andExpect(jsonPath("$.genre", is(it.getGenre())))
                .andExpect(jsonPath("$.rating", is(it.getRating())));
        verify(movieService, VerificationModeFactory.times(1)).createMovie(Mockito.any());
        reset(movieService);
    }
    @Test
    public void givenMovies_whenGetMovies_thenReturnJsonArray() throws Exception {

        MovieEntity it = new MovieEntity(21, "It", 2019, "Horror", 8.4d);
        MovieEntity bee = new MovieEntity(22, "Bee", 2022, "Comedy", 9.1d);

//        MovieEntity it = new MovieEntity("It", 8.4d);
//        MovieEntity bee = new MovieEntity("Bee", 8.8d);

        List<MovieEntity> allMovies = Arrays.asList(it, bee);

        given(movieService.getAllMovies()).willReturn(allMovies);
        mvc.perform(get("/movies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(it.getId())))
                .andExpect(jsonPath("$[0].title", is(it.getTitle())))
                .andExpect(jsonPath("$[0].releaseYear", is(it.getReleaseYear())))
                .andExpect(jsonPath("$[0].genre", is(it.getGenre())))
                .andExpect(jsonPath("$[0].rating", is(it.getRating())))
                .andExpect(jsonPath("$[1].id", is(bee.getId())))
                .andExpect(jsonPath("$[1].title", is(bee.getTitle())))
                .andExpect(jsonPath("$[1].releaseYear", is(bee.getReleaseYear())))
                .andExpect(jsonPath("$[1].genre", is(bee.getGenre())))
                .andExpect(jsonPath("$[1].rating", is(bee.getRating())));

        verify(movieService, VerificationModeFactory.times(1)).getAllMovies();
        reset(movieService);
    }
}
