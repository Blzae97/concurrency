package com.blaze.optimistic.application.bank.account.mapper;

public interface Mapper<E, D> {
    D toDTO(E entity);

    E toEntity(D dto);
}