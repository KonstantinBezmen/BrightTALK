package com.bright.talk.infrastructure.db;

public interface MigrationHelper {

    /**
     * Migrate main schema.
     */
    void migrate();
}
