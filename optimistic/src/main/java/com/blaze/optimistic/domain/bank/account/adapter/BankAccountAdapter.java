package com.blaze.optimistic.domain.bank.account.adapter;

import com.blaze.optimistic.domain.bank.account.entity.BankAccount;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BankAccountAdapter {
    private BankAccount bankAccount;

    @Builder
    public BankAccountAdapter(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BankAccount getBankAccount() {
        return this.bankAccount;
    }
}
