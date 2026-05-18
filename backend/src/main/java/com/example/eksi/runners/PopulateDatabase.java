package com.example.eksi.runners;

import java.sql.Connection;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import com.example.eksi.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Component
public class PopulateDatabase implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(PopulateDatabase.class);

    private final UserRepository userRepository;

    private final DataSource dataSource;

    public PopulateDatabase(UserRepository userRepository, DataSource dataSource) {
        this.userRepository = userRepository;
        this.dataSource = dataSource;
    }

    @Override
    @Transactional
    public void run(String @NonNull ... args) throws Exception {

        try (Connection connection = dataSource.getConnection()) {
            log.info("Database URL: {}", connection.getMetaData().getURL());
            log.info("Database User: {}", connection.getMetaData().getUserName());
        }

        if (userRepository.count() == 0) {
            log.info("[sample-data.sql] Executing to populate database...");
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource("sample-data.sql"));
            populator.execute(dataSource);

            log.info("[sample-data.sql] Database populated.");
        } else {
            log.info("Database is not empty so was not populated again.");
        }
    }
}
