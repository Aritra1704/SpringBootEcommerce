package io.arpaul.ecommerce.config;

import io.arpaul.ecommerce.payload.entity.Country;
import io.arpaul.ecommerce.payload.entity.ProductCategoryEntity;
import io.arpaul.ecommerce.payload.entity.Product;
import io.arpaul.ecommerce.payload.entity.State;
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
    public MyDataRestConfig(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnSupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        //disable httpmethods for PUT, POST and DELETE
        disableHttpMethods(config, theUnSupportedActions, Product.class);
        disableHttpMethods(config, theUnSupportedActions, ProductCategoryEntity.class);
        disableHttpMethods(config, theUnSupportedActions, Country.class);
        disableHttpMethods(config, theUnSupportedActions, State.class);

        // call an internal helper method
        exposeIds(config);
    }

    private void disableHttpMethods(
            RepositoryRestConfiguration config,
            HttpMethod[] theUnSupportedActions,
            Class theClass) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metadata, httpmethods) -> httpmethods.disable(theUnSupportedActions))
                .withCollectionExposure((metadata, httpmethods) -> httpmethods.disable(theUnSupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config) {

        // expose entity ids
        //

        // - get a list of all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // - create an array of the entity types
        List<Class> entityClasses = new ArrayList<>();

        // - get the entity types for the entities
        for (EntityType tempEntityType : entities) {
            entityClasses.add(tempEntityType.getJavaType());
        }

        // - expose the entity ids for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
