package com.sid.bankaccount.service.impl;

import com.sid.bankaccount.DTO.BankAccountRequestDTO;
import com.sid.bankaccount.DTO.BankAccountResponse;
import com.sid.bankaccount.entities.BankAccount;
import com.sid.bankaccount.mappers.AccountMapper;
import com.sid.bankaccount.repository.BankAccountRepository;
import com.sid.bankaccount.service.interfaces.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final BankAccountRepository bankAccountRepository;
    private final AccountMapper accountMapper;

    @Override
    @Transactional(readOnly = true)
    public List<BankAccountResponse> findAll() {
        return accountMapper.toResponses(bankAccountRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BankAccountResponse> findById(String id) {
        return bankAccountRepository.findById(id).map(accountMapper::toResponse);
    }

    @Override
    @Transactional
    public BankAccountResponse createAccount(BankAccountRequestDTO bankAccountRequestDTO) {
        BankAccount bankAccount = accountMapper.toEntity(UUID.randomUUID().toString(), bankAccountRequestDTO);
        return accountMapper.toResponse(bankAccountRepository.save(bankAccount));
    }

    @Override
    @Transactional
    public Optional<BankAccountResponse> updateAccount(String id, BankAccountRequestDTO bankAccountRequestDTO) {
        return bankAccountRepository.findById(id)
                .map(existing -> {
                    accountMapper.updateEntity(bankAccountRequestDTO, existing);
                    return accountMapper.toResponse(bankAccountRepository.save(existing));
                });
    }

    @Override
    @Transactional
    public boolean deleteAccount(String id) {
        if (!bankAccountRepository.existsById(id)) {
            return false;
        }
        bankAccountRepository.deleteById(id);
        return true;
    }
}
