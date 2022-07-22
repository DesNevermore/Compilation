package com.test;

public class GeneralTest {
    StringBuffer tokenTextBuffer = null;    // transient storage of token text

    public static void main(String[] args) {
        int a = 0;
        System.out.println(a=2);
        GeneralTest generalTest = new GeneralTest();
        System.out.println(generalTest.tokenTextBuffer.length());
    }
}
