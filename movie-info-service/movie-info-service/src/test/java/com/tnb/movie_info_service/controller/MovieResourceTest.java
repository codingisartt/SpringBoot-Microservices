package com.tnb.movie_info_service.controller;

import com.tnb.movie_info_service.models.MovieSummary;
import com.tnb.movie_info_service.resources.MovieResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieResource.class)
@TestPropertySource(properties = "api.key=testkey")
public class MovieResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void shouldReturnMovie_whenMovieExists() throws Exception {
        MovieSummary movieSummary = new MovieSummary();
        movieSummary.setTitle("Finding Nemo");

        when(restTemplate.getForObject(
                contains("https://api.themoviedb.org/3/movie/1"),
                eq(MovieSummary.class)
        )).thenReturn(movieSummary);

        mockMvc.perform(get("/movies/12"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movieId").value("12"))
                .andExpect(jsonPath("$.name").value("Finding Nemo"));
    }

}
