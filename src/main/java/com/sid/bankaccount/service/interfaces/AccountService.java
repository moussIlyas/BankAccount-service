package com.sid.bankaccount.service.interfaces;

import com.sid.bankaccount.DTO.BankAccountRequestDTO;
import com.sid.bankaccount.DTO.BankAccountResponse;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<BankAccountResponse> findAll();

    Optional<BankAccountResponse> findById(String id);

    BankAccountResponse createAccount(BankAccountRequestDTO bankAccountRequestDTO);

    Optional<BankAccountResponse> updateAccount(String id, BankAccountRequestDTO bankAccountRequestDTO);

    boolean deleteAccount(String id);
}
