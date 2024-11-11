package com.blaze.optimistic.domain.bank.account.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class BankAccountDTO {
    private Long bankAccountId;
    private Long version;
    private String name;
    private String number;
    private BigDecimal balance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public BankAccountDTO(
            Long bankAccountId,
            Long version,
            String name,
            String number,
            BigDecimal balance,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.bankAccountId = bankAccountId;
        this.version = version;
        this.name = name;
        this.number = number;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
