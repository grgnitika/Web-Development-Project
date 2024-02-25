package com.sanrio_store.sanrio_store.service.impl;

import com.sanrio_store.sanrio_store.dto.request.CustomerRequest;
import com.sanrio_store.sanrio_store.dto.response.CustomerResponse;
import com.sanrio_store.sanrio_store.entity.CustomerEntity;
import com.sanrio_store.sanrio_store.exception.ResourceNotFoundException;
import com.sanrio_store.sanrio_store.mapper.CustomerMapper;
import com.sanrio_store.sanrio_store.repo.CustomerRepo;
import com.sanrio_store.sanrio_store.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepo customerRepo, CustomerMapper customerMapper) {
        this.customerRepo = customerRepo;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerEntity saveCustomer(CustomerRequest customerRequest) {
        CustomerEntity customerEntity = new CustomerEntity(customerRequest);
        return customerRepo.save(customerEntity);
    }

    @Override
    public CustomerEntity updateCustomer(CustomerRequest customerRequest) {
        if (customerRequest.getId() == null) {
            throw new IllegalArgumentException("Customer Id is required.");
        }
        CustomerEntity existingCustomer = customerRepo.findById(customerRequest.getId()).orElseThrow(() -> new ResourceNotFoundException("Customer Id does not exists."));
        existingCustomer.setFullName(customerRequest.getFullName());
        existingCustomer.setAddress(customerRequest.getAddress());
        existingCustomer.setPhoneNumber(customerRequest.getPhoneNumber());
        existingCustomer.setEmail(customerRequest.getEmail());
        return customerRepo.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer Id does not exists."));
        customerRepo.deleteById(id);
    }

    @Override
    public List<CustomerResponse> getCustomers() {
        return customerMapper.getCustomers();
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        customerRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer Id does not exists."));
        return customerMapper.getCustomerById(id);
    }
}
