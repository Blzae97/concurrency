package com.blaze.pessimistic.domain.movie.mapper;

import com.blaze.pessimistic.domain.movie.adapter.MovieRatingAdapter;
import com.blaze.pessimistic.domain.movie.entity.MovieRating;

public class MovingRatingAdapterMapper implements Mapper<MovieRating, MovieRatingAdapter> {
    @Override
    public MovieRatingAdapter toAdapter(MovieRating entity) {
        return new MovieRatingAdapter(entity);
    }
}
