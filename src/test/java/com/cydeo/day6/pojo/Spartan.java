package com.cydeo.day6.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(value="id", allowSetters = true)
// Hey JSON, while serializing the object of this class, whenever you are converting JSON,
// I dont want you to include id value - ONLY for deserialization set the id from json to java
// Whenever deserializing it from json to java, include it.
// Whenever serializing it from java to json, ignore it.
public class Spartan  {
    private int id;
    private String name;
    private String gender;
    private long phone;
}
