package com.sid.bankaccount.service.interfaces;

import com.sid.bankaccount.DTO.CustomerRequestDTO;
import com.sid.bankaccount.DTO.CustomerResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<CustomerResponseDTO> findAll();

    List<CustomerResponseDTO> findByName(String name);

    Optional<CustomerResponseDTO> findById(Long id);

    CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO);

    Optional<CustomerResponseDTO> updateCustomer(Long id, CustomerRequestDTO customerRequestDTO);

    boolean deleteCustomer(Long id);
}
