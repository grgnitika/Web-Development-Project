package com.sanrio_store.sanrio_store.repo;

import com.online.yantra_system.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Integer> {
}
