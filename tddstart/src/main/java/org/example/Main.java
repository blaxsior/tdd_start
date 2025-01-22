package org.example;

public class Main {
    public static class Calculator {
        int add(int a, int b) {
            return a + b;
        }
    }
    public static void main(String[] args) {
        var calc = new Calculator();
        System.out.println(calc.add(1, 2) == 3);
        System.out.println(calc.add(10, 16) == 26);
    }
}