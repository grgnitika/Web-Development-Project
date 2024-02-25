package com.sanrio_store.sanrio_store.service;

import com.sanrio_store.sanrio_store.dto.request.CustomerRequest;
import com.sanrio_store.sanrio_store.dto.response.CustomerResponse;
import com.sanrio_store.sanrio_store.entity.CustomerEntity;

import java.util.List;

public interface CustomerService {
    CustomerEntity saveCustomer(CustomerRequest customerRequest);
    CustomerEntity updateCustomer( CustomerRequest customerRequest);
    void deleteCustomer(Long id);
    List<CustomerResponse> getCustomers();
    CustomerResponse getCustomerById(Long id);
}
