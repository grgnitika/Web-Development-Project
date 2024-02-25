package com.sanrio_store.sanrio_store.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String fullName;

    String phoneNumber;

    String address;

    String email;

    String profilePicture;

    public CustomerEntity(CustomerRequest customerRequest){
        this.id = customerRequest.getId();
        this.fullName = customerRequest.getFullName();
        this.phoneNumber = customerRequest.getPhoneNumber();
        this.address = customerRequest.getAddress();
        this.email = customerRequest.getEmail();
    }


}
