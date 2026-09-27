package com.sid.bankaccount.web;

import com.sid.bankaccount.DTO.CustomerRequestDTO;
import com.sid.bankaccount.DTO.CustomerResponseDTO;
import com.sid.bankaccount.exceptions.CustomerNotFoundException;
import com.sid.bankaccount.service.interfaces.CustomerService;
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
public class CustomerGraphQLController {

    private final CustomerService customerService;

    @QueryMapping
    public List<CustomerResponseDTO> allCustomers() {
        return customerService.findAll();
    }

    @QueryMapping
    public List<CustomerResponseDTO> customersByName(@Argument String name) {
        return customerService.findByName(name);
    }

    @QueryMapping
    public CustomerResponseDTO customerById(@Argument Long id) {
        return customerService.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " + id));
    }

    @MutationMapping
    public CustomerResponseDTO createCustomer(@Argument("input") @Valid CustomerRequestDTO input) {
        return customerService.createCustomer(input);
    }

    @MutationMapping
    public CustomerResponseDTO updateCustomer(@Argument Long id, @Argument("input") @Valid CustomerRequestDTO input) {
        return customerService.updateCustomer(id, input)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " + id));
    }

    @MutationMapping
    public Boolean deleteCustomer(@Argument Long id) {
        return customerService.deleteCustomer(id);
    }
}
