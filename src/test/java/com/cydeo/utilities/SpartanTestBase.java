package com.cydeo.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class SpartanTestBase {
    @BeforeAll
    public static void init() {
        //Save base URL inside this variable so that we don't need to type it in each http method
        //baseURI = "http://34.239.102.62:8000"; instead of this, read from configuration.properties
        baseURI = ConfigurationReader.getProperty("baseURI");

        String dbUrl = "jdbc:oracle:thin:@34.239.102.62:1521:xe";
        String dbUsername = "SP";
        String dbPassword = "SP";

        //DBUtils.createConnection();
    }

    @AfterAll
    public static void tearDown() {
        //DBUtils.destroy();
    }
}
