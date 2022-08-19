package gp.cnusambe.controller;

import org.testcontainers.containers.MySQLContainer;

public class AbstractContainerBaseTest {
    protected static final String MYSQL_IMAGE = "mysql";
    protected static final MySQLContainer MY_SQL_CONTAINER;
    static {
        MY_SQL_CONTAINER = new MySQLContainer<>(MYSQL_IMAGE);
        MY_SQL_CONTAINER.start();
    }
}
