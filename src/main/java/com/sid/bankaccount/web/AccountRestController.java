package com.sid.bankaccount.web;

import com.sid.bankaccount.DTO.BankAccountRequestDTO;
import com.sid.bankaccount.DTO.BankAccountResponse;
import com.sid.bankaccount.service.interfaces.AccountService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class AccountRestController {

    private final AccountService accountService;

    @GetMapping
    public List<BankAccountResponse> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountResponse> findById(@PathVariable String id) {
        return accountService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BankAccountResponse> create(@RequestBody BankAccountRequestDTO bankAccountRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(bankAccountRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccountResponse> update(@PathVariable String id,
                                                      @RequestBody BankAccountRequestDTO bankAccountRequestDTO) {
        return accountService.updateAccount(id, bankAccountRequestDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String id) {
        if (!accountService.deleteAccount(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of("message", "Account deleted: " + id));
    }
}
