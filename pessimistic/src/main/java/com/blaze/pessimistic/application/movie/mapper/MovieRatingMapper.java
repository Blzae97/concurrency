package com.blaze.pessimistic.application.movie.mapper;

import com.blaze.pessimistic.domain.movie.dto.MovieRatingDTO;
import com.blaze.pessimistic.domain.movie.entity.MovieRating;

public class MovieRatingMapper implements Mapper<MovieRating, MovieRatingDTO> {
    @Override
    public MovieRatingDTO toDTO(MovieRating entity) {
        return MovieRatingDTO.builder()
                .movieRatingId(entity.getMovieRatingId())
                .movieFilmId(entity.getMovieFilmId())
                .movieFilmRating(entity.getMovieFilmRating())
                .movieFilmTotalScore(entity.getMovieFilmTotalScore())
                .movieFilmVoterCount(entity.getMovieFilmVoterCount())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public MovieRating toEntity(MovieRatingDTO dto) {
        return MovieRating.builder()
                .movieFilmId(dto.getMovieFilmId())
                .movieFilmRating(dto.getMovieFilmRating())
                .movieFilmTotalScore(dto.getMovieFilmTotalScore())
                .movieFilmVoterCount(dto.getMovieFilmVoterCount())
                .build();
    }
}
