package com.blaze.semaphore.domain.docs.mapper;

public interface Mapper<E, A> {
    A toAdapter(E entity);
}
