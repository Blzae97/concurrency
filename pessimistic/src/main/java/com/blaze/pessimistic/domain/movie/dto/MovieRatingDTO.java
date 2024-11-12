package com.blaze.pessimistic.domain.movie.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class MovieRatingDTO {
    private Long movieRatingId;
    private Long movieFilmId;
    private Long movieFilmRating;
    private Long movieFilmTotalScore;
    private Long movieFilmVoterCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public MovieRatingDTO(
            Long movieRatingId,
            Long movieFilmId,
            Long movieFilmRating,
            Long movieFilmTotalScore,
            Long movieFilmVoterCount,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.movieRatingId = movieRatingId;
        this.movieFilmId = movieFilmId;
        this.movieFilmRating = movieFilmRating;
        this.movieFilmTotalScore = movieFilmTotalScore;
        this.movieFilmVoterCount = movieFilmVoterCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
