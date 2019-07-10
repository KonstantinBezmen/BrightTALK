package com.bright.talk.infrastructure.db;

import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FlywayMigrationHelperTest {

    private FlywayMigrationHelper migrationHelper;

    @Mock
    private Flyway flyway;

    @Before
    public void before() {
        migrationHelper = spy(new FlywayMigrationHelper("url", "user", "pass"));
    }

    @Test
    public void generateKey_ValidKey() {
        doReturn(flyway).when(migrationHelper).getFlyway();
        migrationHelper.migrate();
        verify(migrationHelper, times(1)).getFlyway();
        verify(flyway, times(1)).migrate();
    }
}
