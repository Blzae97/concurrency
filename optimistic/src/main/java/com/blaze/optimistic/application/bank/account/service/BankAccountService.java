package com.blaze.optimistic.application.bank.account.service;

import com.blaze.optimistic.application.bank.account.mapper.BankAccountMapper;
import com.blaze.optimistic.domain.bank.account.adapter.BankAccountAdapter;
import com.blaze.optimistic.domain.bank.account.dto.BankAccountDTO;
import com.blaze.optimistic.domain.bank.account.repository.BankAccountJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class BankAccountService {
    private final BankAccountJpaRepository bankAccountJpaRepository;

    private final Logger LOG = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public BankAccountService(BankAccountJpaRepository bankAccountJpaRepository) {
        this.bankAccountJpaRepository = bankAccountJpaRepository;
    }

    @Transactional
    public BankAccountDTO remittance(String senderBankAccountNumber, String receiverBankAccountNumber, BigDecimal money) {
        // 내 계좌 조회
        BankAccountAdapter senderBankAccount
                = bankAccountJpaRepository.findByBankAccountNumber(senderBankAccountNumber);
        LOG.info("[{}] {} 계좌에는 {} 있습니다.", Thread.currentThread().getId(), senderBankAccount.getBankAccountNumber(), senderBankAccount.getBalance());

        // 내 계좌에서 특정 금액 차감
        senderBankAccount.balanceDecrease(money);

        // 내 계좌 잔액 변경 반영
        BankAccountAdapter savedSenderBankAccount = bankAccountJpaRepository.save(senderBankAccount.getBankAccount());
        LOG.info("[{}] {} 계좌에서 {}를 차감하여 {} 남았습니다.", Thread.currentThread().getId(), savedSenderBankAccount.getBankAccountNumber(), money, savedSenderBankAccount.getBalance());

        // 상대방 계좌 조회
        BankAccountAdapter receiverBankAccount
                = bankAccountJpaRepository.findByBankAccountNumber(receiverBankAccountNumber);
        LOG.info("[{}] {} 계좌에는 {} 있습니다.", Thread.currentThread().getId(), receiverBankAccount.getBankAccountNumber(), receiverBankAccount.getBalance());

        // 상대방 계좌 잔액에 금액 추가
        receiverBankAccount.balanceIncrease(money);

        // 상대방 계좌 잔액 변경 반영
        BankAccountAdapter savedReceiverBankAccount = bankAccountJpaRepository.save(receiverBankAccount.getBankAccount());

        LOG.info("[{}] {} 계좌에서 {}를 증가하여 {} 남았습니다.", Thread.currentThread().getId(), savedReceiverBankAccount.getBankAccountNumber(), money, savedReceiverBankAccount.getBalance());

        // 내 계좌 정보 반환
        BankAccountMapper bankAccountMapper = new BankAccountMapper();
        return bankAccountMapper.toDTO(savedSenderBankAccount.getBankAccount());
    }

}
