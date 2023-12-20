package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyUnitTest {
    @Test
    public void myUnitTest(){
        String result = "onetwo";
        assertEquals("onetwo", result,"My unit test failed");
        System.out.println("test run");
    }
}
