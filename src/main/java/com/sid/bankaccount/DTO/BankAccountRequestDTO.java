package com.sid.bankaccount.DTO;

import com.sid.bankaccount.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class BankAccountRequestDTO {
    private double balance;
    @NotBlank
    private String currency;
    @NotNull
    private AccountType type;
    private Long customerId;
}
