package com.sid.bankaccount.web;

import com.sid.bankaccount.DTO.BankAccountRequestDTO;
import com.sid.bankaccount.DTO.BankAccountResponse;
import com.sid.bankaccount.exceptions.AccountNotFoundException;
import com.sid.bankaccount.service.interfaces.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Controller
@Validated
@RequiredArgsConstructor
public class BankAccountGraphQLController {

    private final AccountService accountService;

    @QueryMapping
    public List<BankAccountResponse> allAccounts() {
        return accountService.findAll();
    }

    @QueryMapping
    public BankAccountResponse accountById(@Argument String id) {
        return accountService.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + id));
    }

    @MutationMapping
    public BankAccountResponse createAccount(@Argument("input") @Valid BankAccountRequestDTO input) {
        return accountService.createAccount(input);
    }

    @MutationMapping
    public BankAccountResponse updateAccount(@Argument String id, @Argument("input") @Valid BankAccountRequestDTO input) {
        return accountService.updateAccount(id, input)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + id));
    }

    @MutationMapping
    public Boolean deleteAccount(@Argument String id) {
        return accountService.deleteAccount(id);
    }
}
