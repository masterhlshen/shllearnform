package com.shl.test;

public class MinAddToMakeValid {
    public static void main(String[] args) {

    }

    public int minAddToMakeValid(String s) {
        int res = 0, need = 0;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(') {
                need += 2;
                if ((need & 1) == 1) {
                    res++;
                    need--;
                }
            } else if (c == ')') {
                need--;
                if (need == -1) {
                    need = 1;
                    res++;
                }
            }
        }
        return res + need;
    }
}
