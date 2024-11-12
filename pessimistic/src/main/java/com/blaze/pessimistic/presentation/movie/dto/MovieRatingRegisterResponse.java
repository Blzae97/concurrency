package com.blaze.pessimistic.presentation.movie.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MovieRatingRegisterResponse {
    private final Long movieFilmRating;;

    @Builder
    public MovieRatingRegisterResponse(Long movieFilmRating) {
        this.movieFilmRating = movieFilmRating;
    }
}
