package com.example.ticketing_system.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean; // Aunque ya no se usa @Bean para el contenedor, se mantiene la importación si hay otros beans
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers // Habilita la gestión del ciclo de vida de los contenedores por JUnit 5
@TestConfiguration
public class TestContainerConfig {

    @Container // Anotación para que JUnit 5 y Testcontainers gestionen este contenedor
    static MySQLContainer<?> mysqlContainer =
            new MySQLContainer<>(DockerImageName.parse("mysql:8.0"))
                    .withDatabaseName("testdb")
                    .withUsername("test")
                    .withPassword("test");

    @DynamicPropertySource // Inyecta propiedades dinámicamente en el contexto de Spring
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mysqlContainer::getUsername);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);
        // Flyway también necesita saber dónde conectarse
        registry.add("spring.flyway.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.flyway.user", mysqlContainer::getUsername);
        registry.add("spring.flyway.password", mysqlContainer::getPassword);
    }

    // El método mysqlContainer() ya no necesita ser un @Bean si solo se usa para @DynamicPropertySource
    // Si tienes otros beans que dependen del contenedor, podrías mantener un @Bean que devuelva el contenedor
    // o inyectar el MySQLContainer directamente en esos beans.
    // Por simplicidad, y dado que @DynamicPropertySource ya lo gestiona, podemos eliminar el @Bean si no hay otros usos.
    // Por ahora, lo dejo comentado si no hay otros usos directos como bean.
    /*
    @Bean
    public MySQLContainer<?> mysqlContainerBean() {
        return mysqlContainer;
    }
    */
}
