package com.sanrio_store.sanrio_store.service;

import com.sanrio_store.sanrio_store.dto.request.CategoryRequest;
import com.sanrio_store.sanrio_store.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    void save(CategoryRequest categoryEntity);
    List<CategoryEntity> getAllCategories();
    CategoryEntity getCategoryById(Integer categoryId);
    void deleteCategory(Integer categoryId);
    void updateCategory(CategoryRequest categoryEntity);
}
