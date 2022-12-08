package com.cydeo.day12;

import com.cydeo.utilities.BookItTestBase;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

public class BookItSpecTest extends BookItTestBase {

    @Test
    public void test1(){
        //send a get request to /api/users/me endpoint as a teacher
        //verify status code and content type
        given()
                .spec(teacherReqSpec)
                .when()
                .get("/api/users/me")
                .then()
                .spec(responseSpec);
    }

    @Test
    public void test2(){
        //send a get request to /api/users/me endpoint as a student-member
        //verify status code and content type

        given()
                .spec(userReqSpec("student-member"))
                .when()
                .get("/api/users/me")
                .then()
                .spec(userCheck("Lorette","Bradnum"));
                //everything is checked inside this
    }

    @Test
    public void test3(){
        //send a get request to /api/users/me endpoint as a teacher
        //verify status code and content type
        given()
                .spec(teacherReqSpec)
                .when()
                .get("/api/users/me")
                .then()
                .spec(getDynamicResSpec(404));
    }
}