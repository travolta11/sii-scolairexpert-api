package org.ssischoolbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
@Configuration
public class DaoConfig {
    @Bean
    @Primary
    public PropertiesFactoryBean sqlQueries() {
        PropertiesFactoryBean prop = new PropertiesFactoryBean();
        ClassPathResource[] resources = new ClassPathResource[] {
                new ClassPathResource("sql/espace.properties"),
                new ClassPathResource("sql/teacher.properties"),
                new ClassPathResource("sql/users.properties")
        };

        prop.setLocations(resources);
        prop.setIgnoreResourceNotFound(true);
        return prop;
    }

}
