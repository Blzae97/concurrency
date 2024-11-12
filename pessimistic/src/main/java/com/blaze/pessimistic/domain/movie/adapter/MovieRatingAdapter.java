package com.blaze.pessimistic.domain.movie.adapter;

import com.blaze.pessimistic.domain.movie.entity.MovieRating;
import lombok.Builder;

public class MovieRatingAdapter {
    private final MovieRating movieRating;

    @Builder
    public MovieRatingAdapter(MovieRating movieRating) {
        this.movieRating = movieRating;
    }

    public MovieRating getMovieRating(){
        return this.movieRating;
    }

    public void voterIncrease(Long value) {
        Long movieFilmVoterCount = this.movieRating.getMovieFilmVoterCount();
        movieFilmVoterCount += value;
        this.movieRating.renewalMovieFilmVoterCount(movieFilmVoterCount);
    }

    public void totalScoreIncrease(Long value) {
        Long movieFilmTotalScore = this.movieRating.getMovieFilmTotalScore();
        movieFilmTotalScore += value;
        this.movieRating.renewalMovieTotalScore(movieFilmTotalScore);
    }

    public void reCalculationMovieFilmRating() {
        Long movieFilmTotalScore = this.movieRating.getMovieFilmTotalScore();
        Long movieFilmVoterCount = this.movieRating.getMovieFilmVoterCount();

        long movieFilmRating = movieFilmTotalScore / movieFilmVoterCount;
        this.movieRating.renewalMovieFilmRating(movieFilmRating);
    }
}
