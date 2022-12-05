package com.cydeo.day5;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {

    @DisplayName("Equation and Number Comparison")
    @Test
    public void numberHamcrest() {

        //FOR EQUATION
        //MatcherAssert.assertThat(5+5, Matchers.is(10));
        //This can be simplified with static import to this:
        assertThat(5 + 5, is(10));

        //Matchers have 2 overloaded version. One accepts value, the other accepts matchers
        assertThat(5 + 5, equalTo(10));
        assertThat(5 + 5, is(equalTo(10)));

        assertThat(5 + 5, not(10));
        assertThat(5 + 5, not(equalTo(10)));
        assertThat(5 + 5, is(not(equalTo(10))));

        //FOR NUMBER COMPARISON
        //greaterThan()
        //greaterThanOrEqualTo()
        //lessThan()
        //lessThanOrEqualTo()
        assertThat(5 + 5, is(greaterThan(9)));
    }


    @DisplayName("Assertion with String")
    @Test
    public void stringHamcrest() {
        //Checking for equality is the same as numbers
        String text = "Hello, Elif";
        assertThat(text, is("Hello, Elif"));
        assertThat(text, equalTo("Hello, Elif"));
        assertThat(text, is(equalTo("Hello, Elif")));

        //Check if it starts with H
        assertThat(text, startsWith("H"));

        //Do this in case-insensitive manner
        assertThat(text, startsWithIgnoringCase("H"));

        //Check if it ends with H
        assertThat(text, endsWith("f"));

        //Do this in case-insensitive manner
        assertThat(text, endsWithIgnoringCase("F"));

        //Check if it ends with H
        assertThat(text, containsString("f"));

        //Do this in case-insensitive manner
        assertThat(text, containsStringIgnoringCase("F"));

        String str = "     ";
        //check if above str is blank
        assertThat(str, blankString());
        assertThat(str, emptyString());

        //check if trimmmed str is empty string
        assertThat(str.trim(), is(emptyString()));
        assertThat(str.trim(), is(blankString()));
    }


    @DisplayName("Hamcrest for Collection")
    @Test
    public void collectionHamcrest() {

        List<Integer> listOfNumbers = Arrays.asList(1,2,4,8,16,32);

        //check the size of the list
        assertThat(listOfNumbers, hasSize(6));

        //check if this list has the item 4
        assertThat(listOfNumbers, hasItem(4));

        //check if this list has the items 4 and 8
        assertThat(listOfNumbers, hasItems(4,8));

        //check if all numbers are greater than 4
        assertThat(listOfNumbers, everyItem(greaterThan(0)));
        //without iterating or using loop, we can check if every item is greater than 0
    }
}
