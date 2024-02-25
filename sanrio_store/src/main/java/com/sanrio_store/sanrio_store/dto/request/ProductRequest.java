package com.sanrio_store.sanrio_store.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class ProductRequest {
    private Integer id;

    @NotNull
    private String itemName;

    @NotNull
    private Integer availableQuantity;

    private String description;

    @NotNull
    private Double price;

    @NotNull
    private MultipartFile image;

    private Integer categoryId;
}
