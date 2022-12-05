package com.cydeo.day10;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class GovXmlTest {
    //https://data.ct.gov/resource/y6p2-px98.xml

    @Test
    public void test1() {
        Response response = given().contentType(ContentType.XML)
                .when()
                .get("https://data.ct.gov/resource/y6p2-px98.xml")
                .then().extract().response();

        XmlPath xmlPath = response.xmlPath();

        //get the phone of row 1
        String phone = xmlPath.getString("response.rows.row[0].phone1");
        System.out.println("phone = " + phone);

        //get all the farmer IDs
        List<String> farmerIds = xmlPath.getList("response.rows.row.farmer_id");
        System.out.println("farmer Ids = " + farmerIds);

        //get the address of the row1
        String address = xmlPath.getString("response.rows.row[0].@_address");
        System.out.println("address = " + address);


    }
}
