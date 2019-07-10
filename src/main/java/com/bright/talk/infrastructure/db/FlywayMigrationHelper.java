package com.bright.talk.infrastructure.db;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FlywayMigrationHelper implements MigrationHelper {

    private String url;

    private String username;

    private String password;

    private Logger logger = LoggerFactory.getLogger(FlywayMigrationHelper.class);

    public FlywayMigrationHelper(
        @Value("${spring.datasource.url}") String url,
        @Value("${spring.datasource.username}") String username,
        @Value("${spring.datasource.password}") String password
    ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public void migrate() {
        logger.info("Migration started");
        try {
            Flyway flyway = getFlyway();
            flyway.migrate();
        } catch (FlywayException e) {
            logger.error(e.getMessage(), e);
        }
        logger.info("Migration finished");
    }

    Flyway getFlyway() {
        return Flyway.configure()
            .dataSource(url, username, password)
            .load();
    }
}
