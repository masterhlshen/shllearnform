package com.shl.test;

public class Test3 {

    public static void main(String[] args) {
        String s = "232";
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            n = n * 10 + (s.charAt(i) - '0');
        }
        System.out.println(n);
    }
}
