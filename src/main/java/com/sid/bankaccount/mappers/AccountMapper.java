package com.sid.bankaccount.mappers;

import com.sid.bankaccount.DTO.BankAccountRequestDTO;
import com.sid.bankaccount.DTO.BankAccountResponse;
import com.sid.bankaccount.entities.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "customer", ignore = true)
    BankAccount toEntity(String id, BankAccountRequestDTO bankAccountRequestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    void updateEntity(BankAccountRequestDTO bankAccountRequestDTO, @MappingTarget BankAccount bankAccount);

    @Mapping(target = "customerId", source = "customer.id")
    BankAccountResponse toResponse(BankAccount bankAccount);

    List<BankAccountResponse> toResponses(List<BankAccount> bankAccounts);
}
