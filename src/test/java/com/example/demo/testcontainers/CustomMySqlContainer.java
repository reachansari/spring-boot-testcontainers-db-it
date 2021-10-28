package com.example.demo.testcontainers;

import lombok.extern.slf4j.Slf4j;
import org.testcontainers.containers.MySQLContainer;

@Slf4j
public class CustomMySqlContainer extends MySQLContainer<CustomMySqlContainer> {

    private static final String DB_IMAGE = "mysql:5.7";
    private static CustomMySqlContainer mysqlContainer;

    private CustomMySqlContainer() {
        super(DB_IMAGE);
    }

    public static synchronized CustomMySqlContainer getInstance() {

        if (mysqlContainer == null) {
            mysqlContainer = new CustomMySqlContainer();
        }
        return mysqlContainer;
    }

    @Override
    public void start() {

        super.start();
        System.setProperty("TEST_DB_URL", mysqlContainer.getJdbcUrl());
        System.setProperty("TEST_DB_USERNAME", mysqlContainer.getUsername());
        System.setProperty("TEST_DB_PASSWORD", mysqlContainer.getPassword());
    
        log.info("started MySql container TEST_DB_URL [{}] TEST_DB_USERNAME [{}] TEST_DB_PASSWORD [{}]");
    }

    @Override
    public void stop() {
        
    }
}
