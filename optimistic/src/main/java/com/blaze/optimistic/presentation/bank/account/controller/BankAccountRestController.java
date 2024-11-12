package com.blaze.optimistic.presentation.bank.account.controller;

import com.blaze.optimistic.application.bank.account.service.BankAccountService;
import com.blaze.optimistic.domain.bank.account.dto.BankAccountDTO;
import com.blaze.optimistic.presentation.bank.account.dto.RemittanceRequest;
import com.blaze.optimistic.presentation.bank.account.dto.RemittanceResponse;
import com.blaze.optimistic.presentation.bank.account.mapper.RemittanceResponseMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class BankAccountRestController {

    private final BankAccountService bankAccountService;

    public BankAccountRestController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping(value = "/remittance")
    public RemittanceResponse remittance(@RequestBody RemittanceRequest remittanceRequest) {
        BankAccountDTO bankAccountDTO = bankAccountService.remittance(
                remittanceRequest.getSenderBankAccountNumber(),
                remittanceRequest.getReceiverBankAccountNumber(),
                remittanceRequest.getMoney()
        );

        RemittanceResponseMapper remittanceResponseMapper = new RemittanceResponseMapper();
        return remittanceResponseMapper.toResponse(bankAccountDTO);
    }
}
