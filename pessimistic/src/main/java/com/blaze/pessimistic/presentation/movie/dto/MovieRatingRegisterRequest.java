package com.blaze.pessimistic.presentation.movie.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MovieRatingRegisterRequest {
    private final Long movieFilmId;
    private final Long movieFilmRating;

    @Builder
    public MovieRatingRegisterRequest(Long movieFilmId, Long movieFilmRating) {
        this.movieFilmId = movieFilmId;
        this.movieFilmRating = movieFilmRating;
    }
}
