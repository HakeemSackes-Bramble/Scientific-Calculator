package com.example.hakeemsackes_bramble.scientificcalculator;

import java.math.BigInteger;

import static com.example.hakeemsackes_bramble.scientificcalculator.MainActivity.equation;

/**
 * Created by hakeemsackes-bramble on 10/5/16.
 */

class Equations {


    static BigInteger factorial(double input) {

        BigInteger starter = BigInteger.valueOf(1);
        if (input == 0) {
            starter = BigInteger.valueOf(1);
        } else {

            for (int i = 1; i <= input; i++) {  //input is the number that precedes the factorial sign
                starter = starter.multiply(BigInteger.valueOf(i));
            }

        }
        return starter;
    }

    static double add(double input1, double input2) {
        return input1 + input2;
    }

    static double divide(double input1, double input2) {
        return input1 / input2;
    }

    static double subtract(double input1, double input2) {
        return input1 - input2;
    }

    static double multiply(double input1, double input2) {
        return input1 * input2;
    }

    static double power(double input1, double input2) {
        return Math.pow(input1, input2);
    }

    static double log(double input1) {
        return Math.log(input1);
    }

    static double sin(double input1) {
        return Math.sin(input1);
    }

    static double cos(double input1) {
        return Math.cos(input1);
    }

    static double tan(double input1) {
        return Math.tan(input1);
    }

    static double pi() {
        return Math.PI;
    }

    static double e() {
        return Math.E;
    }

    static double ln(double input1) {
        return (Math.log(input1)) / Math.log(Math.E);
    }

    static void parenthesis(int stack, int queue) {
        Answer.doPemdas(equation.subList(stack, queue));
    }

}
