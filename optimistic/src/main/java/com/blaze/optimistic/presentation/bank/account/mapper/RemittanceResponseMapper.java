package com.blaze.optimistic.presentation.bank.account.mapper;

import com.blaze.optimistic.domain.bank.account.dto.BankAccountDTO;
import com.blaze.optimistic.presentation.bank.account.dto.RemittanceResponse;

public class RemittanceResponseMapper implements Mapper<BankAccountDTO, RemittanceResponse> {
    @Override
    public RemittanceResponse toResponse(BankAccountDTO dto) {
        return RemittanceResponse.builder()
                .bankAccountNumber(dto.getNumber())
                .balance(dto.getBalance())
                .build();
    }
}
