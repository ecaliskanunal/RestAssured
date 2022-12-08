package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import io.restassured.http.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.*;

import static io.restassured.RestAssured.*;

public class BookItParameterizedTest {


    public static List<Map<String,String>> getExcelData(){
        ExcelUtil bookitFile = new ExcelUtil("src/test/resources/BookItQa3.xlsx","QA3");
        return bookitFile.getDataList();
    }

    @ParameterizedTest
    @MethodSource("getExcelData") // pass here the name of the Excel file
    public void bookItTest(Map<String,String> user){
        System.out.println("user.get(\"email\") = " + user.get("email"));
        System.out.println("user.get(\"password\") = " + user.get("password"));

        given()
                .accept(ContentType.JSON)
                .baseUri("https://cybertek-reservation-api-qa3.herokuapp.com")
                .queryParams(user) //I pass map directly because query param keys and map keys are equal - the same
                .when()
                .get("/sign")
                .then()
                .statusCode(200)
                .log().body();
    }

}