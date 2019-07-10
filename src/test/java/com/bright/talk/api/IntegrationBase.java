package com.bright.talk.api;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

//Class was copied from the last project to save a time
@ActiveProfiles("test")
public class IntegrationBase {

    private int port;

    private BasicDataSource mainDataSource;

    @Autowired
    public void setPort(@LocalServerPort int port) {
        this.port = port;
    }

    @Autowired
    public void setMainDataSource(BasicDataSource mainDataSource) {
        this.mainDataSource = mainDataSource;
    }

    @Before
    public void beforeEach() {
        executeSqlFileToDataStorage("db/h2_database.sql", mainDataSource);
    }

    private void executeSqlFileToDataStorage(String fileName, DataSource dataSource) {
        Resource resource = new ClassPathResource(fileName);
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
        databasePopulator.execute(dataSource);
    }

    protected IntegrationBase beforeTest(Runnable runnable) {
        runnable.run();
        return this;
    }

    public IntegrationBase and(Runnable runnable) {
        runnable.run();
        return this;
    }

    public IntegrationBase then() {
        return this;
    }

    public RestApiTest sendRequestToEndpoint(String endpoint) {
        return RestApiTest
            .sendRequestToEndpoint(endpoint)
            .usingPort(port);
    }
}
