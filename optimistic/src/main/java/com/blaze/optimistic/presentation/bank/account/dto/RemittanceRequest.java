package com.blaze.optimistic.presentation.bank.account.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class RemittanceRequest {
    private final String senderBankAccountNumber;
    private final String receiverBankAccountNumber;
    private final BigDecimal money;

    @Builder
    public RemittanceRequest(String senderBankAccountNumber, String receiverBankAccountNumber, BigDecimal money) {
        this.senderBankAccountNumber = senderBankAccountNumber;
        this.receiverBankAccountNumber = receiverBankAccountNumber;
        this.money = money;
    }
}
