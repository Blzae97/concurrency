package com.blaze.optimistic.domain.bank.account.adapter;

import com.blaze.optimistic.domain.bank.account.entity.BankAccount;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class BankAccountAdapter {
    private final BankAccount bankAccount;

    @Builder
    public BankAccountAdapter(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BankAccount getBankAccount() {
        return this.bankAccount;
    }

    public String getBankAccountNumber(){
        return this.bankAccount.getNumber();
    }

    public BigDecimal getBalance(){
        return this.bankAccount.getBalance();
    }

    public void balanceIncrease(BigDecimal money) {
        BigDecimal balance = this.bankAccount.getBalance();
        this.bankAccount.updateBalance(balance.add(money));
    }

    public void balanceDecrease(BigDecimal money) {
        BigDecimal balance = this.bankAccount.getBalance();
        this.bankAccount.updateBalance(balance.subtract(money));
    }
}
