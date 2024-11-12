package com.blaze.optimistic.application.bank.account.mapper;

import com.blaze.optimistic.domain.bank.account.dto.BankAccountDTO;
import com.blaze.optimistic.domain.bank.account.entity.BankAccount;

public class BankAccountMapper implements Mapper<BankAccount, BankAccountDTO> {
    @Override
    public BankAccountDTO toDTO(BankAccount entity) {
        return BankAccountDTO.builder()
                .bankAccountId(entity.getBankAccountId())
                .number(entity.getNumber())
                .name(entity.getName())
                .balance(entity.getBalance())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public BankAccount toEntity(BankAccountDTO dto) {
        return BankAccount.builder()
                .number(dto.getNumber())
                .name(dto.getName())
                .balance(dto.getBalance())
                .build();
    }
}