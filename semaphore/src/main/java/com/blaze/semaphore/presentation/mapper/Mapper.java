package com.blaze.semaphore.presentation.mapper;

public interface Mapper<D, R> {
    R toFetchResponse(D dto);
}
