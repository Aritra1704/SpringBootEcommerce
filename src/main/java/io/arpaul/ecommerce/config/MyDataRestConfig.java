package io.arpaul.ecommerce.config;

import io.arpaul.ecommerce.payload.entity.ProductCategoryEntity;
import io.arpaul.ecommerce.payload.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig  implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnSupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        //disable httpmethods for Product: PUT, POST and DELETE
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure((metadata, httpmethods) -> httpmethods.disable(theUnSupportedActions))
                .withCollectionExposure((metadata, httpmethods) -> httpmethods.disable(theUnSupportedActions));

        //disable httpmethods for ProductCategory: PUT, POST and DELETE
        config.getExposureConfiguration()
                .forDomainType(ProductCategoryEntity.class)
                .withItemExposure((metadata, httpmethods) -> httpmethods.disable(theUnSupportedActions))
                .withCollectionExposure((metadata, httpmethods) -> httpmethods.disable(theUnSupportedActions));

        exposeIds(config);
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        List<Class> entityClass = new ArrayList<>();
        for(EntityType entityType : entities) {
            entityClass.add(entityType.getJavaType());
        }

        Class[] domainType = entityClass.toArray(new Class[0]);
        config.exposeIdsFor(domainType);
    }
}
