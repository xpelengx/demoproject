package com.example.demo.configuration;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
class FlywayConfiguration {

    @Value("${spring.flyway.locations}")
    String locations;
    DataSource dataSource;
    @Value("${spring.flyway.schemas:rss}")
    private String flywaySchemas;

    @Autowired
    public FlywayConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void migrate() {
        FluentConfiguration fluentConfiguration = new FluentConfiguration();
        fluentConfiguration.dataSource(dataSource)
                .baselineOnMigrate(true)
                .table("messaging_schema_history")
                .schemas(flywaySchemas)
                .locations(locations);
        Flyway flyway = new Flyway(fluentConfiguration);
        flyway.migrate();
    }

}
