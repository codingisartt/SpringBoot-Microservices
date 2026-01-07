package com.tnb.movie_catalog_service.services;

import com.tnb.movie_catalog_service.models.CatalogItem;
import com.tnb.movie_catalog_service.models.Movie;
import com.tnb.movie_catalog_service.models.Rating;
import com.tnb.movie_catalog_service.resources.services.MovieInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieInfoTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private MovieInfo movieInfo;

    @Test
    void shouldReturnCatalogItem_whenMovieServiceIsUp() {
        Rating rating = new Rating("1", 4);
        Movie movie = new Movie("1", "Inception");

        when(restTemplate.getForObject(
                "http://movie-info-service/movies/1",
                Movie.class
        )).thenReturn(movie);

        CatalogItem item = movieInfo.getCatalogItem(rating);

        assertEquals("Inception", item.getName());
        assertEquals(4, item.getRating());
    }

    @Test
    void shouldReturnFallbackCatalogItem() {
        Rating rating = new Rating("1", 4);

        CatalogItem fallback = movieInfo.getFallbackCatalogItem(rating);


        assertEquals("Movie name fallback", fallback.getName());
        assertEquals(0, fallback.getRating());
    }
}
