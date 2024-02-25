package com.sanrio_store.sanrio_store.entity;

import jakarta.persistence.*;

public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id", referencedColumnName = "id")
    private CustomerEntity customerEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id", referencedColumnName = "id")
    private ProductEntity productEntity;

    private Integer quantity;

    @Column(name = "total_price")
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private BookingEnum status;






}
