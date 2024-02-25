package com.sanrio_store.sanrio_store.entity;

import jakarta.persistence.*;

public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String itemName;

    private Integer availableQuantity;

    private String description;

    private Double price;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private CategoryEntity categoryEntity;


}
