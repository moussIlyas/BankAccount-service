package com.sid.bankaccount;

import com.sid.bankaccount.entities.BankAccount;
import com.sid.bankaccount.enums.AccountType;
import com.sid.bankaccount.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class BankAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(BankAccountRepository bankAccountRepository) {
        return args -> {
            if (bankAccountRepository.count() > 0) {
                return;
            }
            AtomicLong sequence = new AtomicLong(1);
            List<BankAccount> bankAccounts = List.of(
                    BankAccount.builder().id(Long.toString(sequence.getAndIncrement())).type(AccountType.CURRENT).build(),
                    BankAccount.builder().id(Long.toString(sequence.getAndIncrement())).type(AccountType.SAVING).build(),
                    BankAccount.builder().id(Long.toString(sequence.getAndIncrement())).type(AccountType.CURRENT).build()
            );
            bankAccountRepository.saveAll(bankAccounts);
        };
    }
}
