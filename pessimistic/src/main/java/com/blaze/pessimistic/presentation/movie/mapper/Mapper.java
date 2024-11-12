package com.blaze.pessimistic.presentation.movie.mapper;

public interface Mapper<D, R> {
    R toResponse(D dto);
}
