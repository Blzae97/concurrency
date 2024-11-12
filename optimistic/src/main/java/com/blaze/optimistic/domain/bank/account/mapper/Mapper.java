package com.blaze.optimistic.domain.bank.account.mapper;

public interface Mapper<E, A> {
    A toAdapter(E entity);
}
