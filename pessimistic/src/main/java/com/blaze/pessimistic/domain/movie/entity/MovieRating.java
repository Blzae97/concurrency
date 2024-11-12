package com.blaze.pessimistic.domain.movie.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Table(name = "movie_rating")
@Entity
@EntityListeners(value = AuditingEntityListener.class)
public class MovieRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_rating_id", columnDefinition = "int(11)")
    private Long movieRatingId;

    @Column(name = "movie_film_id", columnDefinition = "int(11)")
    private Long movieFilmId;

    @Column(name = "movie_film_rating", columnDefinition = "int(11)")
    private Long movieFilmRating;

    @Column(name = "movie_film_total_score", columnDefinition = "int(11)")
    private Long movieFilmTotalScore;

    @Column(name = "movie_film_voter_count", columnDefinition = "int(11)")
    private Long movieFilmVoterCount;

    @CreatedDate
    @Column(name = "created_at", columnDefinition = "timestamp(3)")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "timestamp(3)")
    private LocalDateTime updatedAt;

    @Builder
    public MovieRating(
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

    public void renewalMovieFilmVoterCount(Long movieFilmVoterCount) {
        this.movieFilmVoterCount = movieFilmVoterCount;
    }

    public void renewalMovieTotalScore(Long movieFilmTotalScore) {
        this.movieFilmTotalScore = movieFilmTotalScore;
    }

    public void renewalMovieFilmRating(Long movieFilmRating) {
        this.movieFilmRating = movieFilmRating;
    }
}
