package com.sanrio_store.sanrio_store.repo;

import com.online.yantra_system.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<ProductEntity, Integer> {
}
