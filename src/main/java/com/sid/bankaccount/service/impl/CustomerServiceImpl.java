package com.sid.bankaccount.service.impl;

import com.sid.bankaccount.DTO.CustomerRequestDTO;
import com.sid.bankaccount.DTO.CustomerResponseDTO;
import com.sid.bankaccount.entities.Customer;
import com.sid.bankaccount.mappers.CustomerMapper;
import com.sid.bankaccount.repository.CustomerRepository;
import com.sid.bankaccount.service.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> findAll() {
        return customerMapper.toResponses(customerRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> findByName(String name) {
        return customerMapper.toResponses(customerRepository.findByNameContainingIgnoreCase(name));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerResponseDTO> findById(Long id) {
        return customerRepository.findById(id).map(customerMapper::toResponse);
    }

    @Override
    @Transactional
    public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {
        return customerMapper.toResponse(customerRepository.save(customerMapper.toEntity(customerRequestDTO)));
    }

    @Override
    @Transactional
    public Optional<CustomerResponseDTO> updateCustomer(Long id, CustomerRequestDTO customerRequestDTO) {
        return customerRepository.findById(id)
                .map(existing -> {
                    customerMapper.updateEntity(customerRequestDTO, existing);
                    return customerMapper.toResponse(customerRepository.save(existing));
                });
    }

    @Override
    @Transactional
    public boolean deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            return false;
        }
        customerRepository.deleteById(id);
        return true;
    }
}
