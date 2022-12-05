package com.cydeo.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class SpartanAuthTestBase {

    @BeforeAll
    public static void init() {
        //Save base URL inside this variable so that we don't need to type it in each http method
        baseURI = "http://34.239.102.62:7000";

    }

    @AfterAll
    public static void tearDown() {
        //DBUtils.destroy();
    }
}

