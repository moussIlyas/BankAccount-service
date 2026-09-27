package com.sid.bankaccount.web;

import com.sid.bankaccount.entities.BankAccount;
import com.sid.bankaccount.repository.BankAccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountRestController {

    private final BankAccountRepository bankAccountRepository;

    public AccountRestController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @GetMapping
    public List<BankAccount> findAll() {
        return bankAccountRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> findById(@PathVariable String id) {
        return bankAccountRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BankAccount> save(@RequestBody BankAccount bankAccount) {
        boolean alreadyExists = bankAccountRepository.existsById(bankAccount.getId());
        BankAccount saved = bankAccountRepository.save(bankAccount);
        return alreadyExists
                ? ResponseEntity.ok(saved)
                : ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccount> update(@PathVariable String id, @RequestBody BankAccount bankAccount) {
        return bankAccountRepository.findById(id)
                .map(existing -> {
                    existing.setBalance(bankAccount.getBalance());
                    existing.setCurrency(bankAccount.getCurrency());
                    existing.setType(bankAccount.getType());
                    return ResponseEntity.ok(bankAccountRepository.save(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String id) {
        if (!bankAccountRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bankAccountRepository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Account deleted: " + id));
    }
}
