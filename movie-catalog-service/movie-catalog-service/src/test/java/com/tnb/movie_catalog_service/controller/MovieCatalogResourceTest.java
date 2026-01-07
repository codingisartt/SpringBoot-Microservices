package com.tnb.movie_catalog_service.controller;

import com.tnb.movie_catalog_service.models.CatalogItem;
import com.tnb.movie_catalog_service.models.Rating;
import com.tnb.movie_catalog_service.models.UserRating;
import com.tnb.movie_catalog_service.resources.MovieCatalogResource;
import com.tnb.movie_catalog_service.resources.services.MovieInfo;
import com.tnb.movie_catalog_service.resources.services.UserRatingInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieCatalogResource.class)
public class MovieCatalogResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieInfo movieInfo;

    @MockBean
    private UserRatingInfo userRatingInfo;

    @Test
    void shouldReturnCatalog_forUser() throws Exception {
        // given
        Rating rating = new Rating("1", 5);
        UserRating userRating = new UserRating();
        userRating.setUserId("user1");
        userRating.setUserRating(List.of(rating));

        CatalogItem catalogItem =
                new CatalogItem("Inception", "desc", 5);

        when(userRatingInfo.getUserRating("user1"))
                .thenReturn(userRating);

        when(movieInfo.getCatalogItem(rating))
                .thenReturn(catalogItem);

        // when + then
        mockMvc.perform(get("/catalog/user1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Inception"))
                .andExpect(jsonPath("$[0].rating").value(5));
    }
}
