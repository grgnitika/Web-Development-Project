package com.sanrio_store.sanrio_store.service;


import com.sanrio_store.sanrio_store.dto.request.ProductRequest;
import com.sanrio_store.sanrio_store.dto.response.CategoryItemResponse;
import com.sanrio_store.sanrio_store.dto.response.ProductResponse;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    void save(ProductRequest itemEntity) throws IOException;
    List<ProductResponse> getAllItem();
    ProductResponse getItemById(Long itemId);
    void deleteItem(Long itemId);
    void updateItem(ProductRequest itemEntity) throws IOException;
    List<ProductResponse> getItemListByCategoryId(Long categoryId);
    List<CategoryItemResponse> getItemAndCategory();
}
