package io.arpaul.ecommerce.config;

import io.arpaul.ecommerce.payload.entity.ProductCategoryEntity;
import io.arpaul.ecommerce.payload.entity.ProductEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig  implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnSupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        //disable httpmethods for Product: PUT, POST and DELETE
        config.getExposureConfiguration()
                .forDomainType(ProductEntity.class)
                .withItemExposure((metadata, httpmethods) -> httpmethods.disable(theUnSupportedActions))
                .withCollectionExposure((metadata, httpmethods) -> httpmethods.disable(theUnSupportedActions));

        //disable httpmethods for ProductCategory: PUT, POST and DELETE
        config.getExposureConfiguration()
                .forDomainType(ProductCategoryEntity.class)
                .withItemExposure((metadata, httpmethods) -> httpmethods.disable(theUnSupportedActions))
                .withCollectionExposure((metadata, httpmethods) -> httpmethods.disable(theUnSupportedActions));
    }
}
