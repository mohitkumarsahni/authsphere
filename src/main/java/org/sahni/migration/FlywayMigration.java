package org.sahni.migration;

import io.quarkus.logging.Log;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.output.MigrateResult;

@ApplicationScoped
public class FlywayMigration {

    @ConfigProperty(name = "quarkus.flyway.enabled")
    boolean runMigration;

    @ConfigProperty(name = "quarkus.flyway.jdbc-url")
    String quarkusFlywayJdbcUrl;

    @ConfigProperty(name = "quarkus.flyway.username")
    String quarkusFlywayUsername;

    @ConfigProperty(name = "quarkus.flyway.password")
    String quarkusFlywayPassword;

    @ConfigProperty(name = "quarkus.flyway.table")
    String quarkusFlywayTable;

    public void runFlywayMigration(@Observes StartupEvent event) {
        if (runMigration) {
            Flyway flyway = Flyway
                    .configure()
                    .dataSource(quarkusFlywayJdbcUrl, quarkusFlywayUsername, quarkusFlywayPassword)
                    .table(quarkusFlywayTable)
                    .load();
            MigrateResult migrateResult = flyway.migrate();
            if (migrateResult.success) {
                Log.infof("migration successful. current migration version: %s", migrateResult.targetSchemaVersion);
            }
        }
    }

}
