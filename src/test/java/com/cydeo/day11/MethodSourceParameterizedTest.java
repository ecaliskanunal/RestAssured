package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.*;

public class MethodSourceParameterizedTest {


    @ParameterizedTest
    @MethodSource("getNames")
    public void testPrintNames(String name) {
        System.out.println("name = " + name);
    }

    public static List<String> getNames() {
        //you can get value from anywhere almost anytype and return to your test
        //DB
        //Excel
        //other APIs

        List<String> nameList = Arrays.asList("Parvin", "Nasim", "mohamad", "Nadir", "Saim", "Gurhan", "Murodil");
        return nameList;
    }

    // I want to read data in a parameterized way from Excel
    @ParameterizedTest
    @MethodSource("getExcelData")
    public void excelParamTest(Map<String, String> userInfo) {
        System.out.println("FirstName: " + userInfo.get("firstname"));
        System.out.println("LastName: " + userInfo.get("lastname"));
    }

    // Each element of getExcelData is a Map, so you pass map as the parameter of the method above - excelParamTest
    // By this way, we are using test data in an Excel file, not within my test code
    public static List<Map<String, String>> getExcelData() {
        //get your file object
        ExcelUtil vytrackFile = new ExcelUtil("src/test/resources/Vytracktestdata.xlsx", "QA3-all");
        //return sheet as a list of map
        return vytrackFile.getDataList();
    }



}
