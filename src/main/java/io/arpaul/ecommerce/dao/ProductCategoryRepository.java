package io.arpaul.ecommerce.dao;

import io.arpaul.ecommerce.payload.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(
        collectionResourceRel = "productCategory",
        path = "product-category"
)
@CrossOrigin("http://localhost:4200")
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Long> {
}
