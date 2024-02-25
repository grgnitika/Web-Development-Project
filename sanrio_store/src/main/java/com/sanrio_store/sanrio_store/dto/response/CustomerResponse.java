package com.sanrio_store.sanrio_store.dto.response;

import lombok.Data;

@Data
public class CustomerResponse {
    Long id;

    String fullName;

    String phoneNumber;

    String address;

    String email;
}
