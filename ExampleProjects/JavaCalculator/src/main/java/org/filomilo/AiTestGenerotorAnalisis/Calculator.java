package org.filomilo.AiTestGenerotorAnalisis;

public class Calculator {

    public static int add(int a, int b) {
        return a + b;
    }
    public static int subtract(int a, int b) {
        return a - b;
    }
    public static int multiply(int a, int b) {
        return a * b;
    }
    public static int divide(int a, int b) {
        if (b == 0)
            throw new ArithmeticException("Divide by zero");
        return a / b;
    }

    public static float multiply(float a, float b) {
        return a * b;
    }
    public static float divide(float a, float b) {
        if(b == 0)
            throw new ArithmeticException("Divide by zero");
        return a / b;
    }
    public static float add(float a, float b) {
        return a + b;
    }
    public static float subtract(float a, float b) {
        return a - b;
    }



    public static double multiply(double a, double b) {
        return a * b;
    }
    public static double divide(double a, double b) {
        if(b == 0)
            throw new ArithmeticException("Divide by zero");
        return a / b;
    }
    public static double add(double a, double b) {
        return a + b;
    }
    public static double subtract(double a, double b) {
        return a - b;
    }


}
