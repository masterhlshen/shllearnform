package com.shl.test;

public class Fbnc {
    public static void main(String[] args) {
        System.out.println(fbnc(11));
    }

    static int fbnc(int n) {
        if (n == 1 || n == 2) return 1;
        return fbnc(n - 1) + fbnc(n - 2);
    }
}
