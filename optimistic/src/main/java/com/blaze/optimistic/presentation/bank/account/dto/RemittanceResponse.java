package com.blaze.optimistic.presentation.bank.account.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class RemittanceResponse {
    private final String bankAccountNumber;
    private final BigDecimal balance;

    @Builder
    public RemittanceResponse(String bankAccountNumber, BigDecimal balance) {
        this.bankAccountNumber = bankAccountNumber;
        this.balance = balance;
    }
}
