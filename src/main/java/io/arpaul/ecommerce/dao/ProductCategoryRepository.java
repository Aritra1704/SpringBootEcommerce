package io.arpaul.ecommerce.dao;

import io.arpaul.ecommerce.payload.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
        collectionResourceRel = "productCategory",
        path = "product-category"
)
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Long> {
}
