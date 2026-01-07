package com.tnb.movie_catalog_service.services;

import com.tnb.movie_catalog_service.models.UserRating;
import com.tnb.movie_catalog_service.resources.services.UserRatingInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRatingInfoTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UserRatingInfo userRatingInfo;

    @Test
    void shouldReturnUserRating_whenServiceIsUp() {
        // given
        UserRating mockRating = new UserRating();
        mockRating.setUserId("user1");

        when(restTemplate.getForObject(
                "http://ratings-data-service/ratingsdata/users/user1",
                UserRating.class
        )).thenReturn(mockRating);

        UserRating result = userRatingInfo.getUserRating("user1");

        assertEquals("user1", result.getUserId());
    }

    @Test
    void shouldReturnFallback_whenServiceFails() {
        UserRating fallback = userRatingInfo.getFallbackUserRating("user1");

        assertEquals("user1", fallback.getUserId());
        assertEquals(1, fallback.getUserRating().size());
        assertEquals(0, fallback.getUserRating().get(0).getRating());
    }
}
