package io.arpaul.ecommerce.dao;

import io.arpaul.ecommerce.payload.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
