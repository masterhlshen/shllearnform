package com.shl.playalgorithm.linealg;

import java.util.Arrays;

/**
 * 反转字符串
 * <a href="https://leetcode.cn/problems/reverse-string/" />
 */
public class ReverseString {
    public void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        int left = 0, right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        char[] s = new char[]{'a', 'b', 'c'};
        ReverseString reverseString = new ReverseString();
        reverseString.reverseString(s);
        System.out.println(Arrays.toString(s));
    }
}
