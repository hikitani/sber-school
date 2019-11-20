package com.sbt.javaschool.rnd.lesson6;

public class CalculatorImpl implements Calculator{

    @PerfCalc
    public int calculate(int num) {
        int res = 1;
        if (num < 0) {
            return -1;
        }
        if (num == 0 || num == 1) {
            return res;
        }
        for (int i = 2; i <= num; i++) {
            res *= i;
        }
        return res;
    }
}
