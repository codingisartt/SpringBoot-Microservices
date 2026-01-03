package com.tnb.movie_catalog_service.resources.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tnb.movie_catalog_service.models.CatalogItem;
import com.tnb.movie_catalog_service.models.Movie;
import com.tnb.movie_catalog_service.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getName(), "test desc", rating.getRating());
    }

    public CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("Movie name fallback", "test fallback desc", 0);
    }
}
