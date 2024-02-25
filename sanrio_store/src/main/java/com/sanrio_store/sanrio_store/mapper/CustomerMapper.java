package com.sanrio_store.sanrio_store.mapper;

import com.sanrio_store.sanrio_store.dto.response.CustomerResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface CustomerMapper {
    List<CustomerResponse> getCustomers();

    CustomerResponse getCustomerById(@Param("id") Long id);
}
