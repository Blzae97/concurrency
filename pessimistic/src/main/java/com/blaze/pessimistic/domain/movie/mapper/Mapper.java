package com.blaze.pessimistic.domain.movie.mapper;

public interface Mapper<E, A> {
    A toAdapter(E entity);
}
