package com.sanrio_store.sanrio_store.mapper;

import com.sanrio_store.sanrio_store.dto.response.CategoryItemResponse;
import com.sanrio_store.sanrio_store.dto.response.ProductResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductResponse> getAllProducts();

    List<CategoryItemResponse> getAllProductsCategories();

    List<ProductResponse> getProductsListByCategoryId(@Param("id") Long id);

    ProductResponse getItemById(@Param("id") Long id);
}
