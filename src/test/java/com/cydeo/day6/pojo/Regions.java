package com.cydeo.day6.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties (ignoreUnknown = true)
public class Regions {
    private List<Region> items;
    private int count;
    // I just want to get these keys, so I want to ignore the remaining keys.
    // In order to do this, I"m gonna put an annotation above this pojo class
}
