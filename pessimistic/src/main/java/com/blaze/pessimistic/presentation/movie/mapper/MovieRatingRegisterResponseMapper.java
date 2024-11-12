package com.blaze.pessimistic.presentation.movie.mapper;

import com.blaze.pessimistic.domain.movie.dto.MovieRatingDTO;
import com.blaze.pessimistic.presentation.movie.dto.MovieRatingRegisterResponse;

public class MovieRatingRegisterResponseMapper implements Mapper<MovieRatingDTO, MovieRatingRegisterResponse> {
    @Override
    public MovieRatingRegisterResponse toResponse(MovieRatingDTO dto) {
        return MovieRatingRegisterResponse.builder()
                .movieFilmRating(dto.getMovieFilmRating())
                .build();
    }
}
