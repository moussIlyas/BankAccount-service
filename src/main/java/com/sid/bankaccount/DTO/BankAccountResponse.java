package com.sid.bankaccount.DTO;

import com.sid.bankaccount.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountResponse {
    private String id;
    private double balance;
    private String currency;
    private AccountType type;
}
