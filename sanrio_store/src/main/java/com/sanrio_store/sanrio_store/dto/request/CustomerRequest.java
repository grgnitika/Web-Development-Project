package com.sanrio_store.sanrio_store.dto.request;

import lombok.Data;

@Data
public class CustomerRequest {
    Long id;

    String fullName;

    String phoneNumber;

    String address;

    String email;
}
