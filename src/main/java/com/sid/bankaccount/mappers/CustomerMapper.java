package com.sid.bankaccount.mappers;

import com.sid.bankaccount.DTO.CustomerRequestDTO;
import com.sid.bankaccount.DTO.CustomerResponseDTO;
import com.sid.bankaccount.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bankAccounts", ignore = true)
    Customer toEntity(CustomerRequestDTO customerRequestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bankAccounts", ignore = true)
    void updateEntity(CustomerRequestDTO customerRequestDTO, @MappingTarget Customer customer);

    CustomerResponseDTO toResponse(Customer customer);

    List<CustomerResponseDTO> toResponses(List<Customer> customers);
}
