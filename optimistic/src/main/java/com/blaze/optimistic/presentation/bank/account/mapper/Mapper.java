package com.blaze.optimistic.presentation.bank.account.mapper;

public interface Mapper<D, R> {
    R toResponse(D dto);
}
