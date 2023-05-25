package com.shl.playalgorithm.linealg;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串
 * <a href="" />
 */
public class LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>(s.length());
        int left = 0, right = 0,  res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;

            if (window.containsKey(c)) {
                window.put(c, window.get(c) + 1);
            } else {
                window.put(c, 1);
            }

            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                window.put(d, window.getOrDefault(d, 0) - 1);
            }

            res = Math.max(res, right - left);

        }
        return res;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        LengthOfLongestSubstring lengthOfLongestSubstring = new LengthOfLongestSubstring();
        int res = lengthOfLongestSubstring.lengthOfLongestSubstring(s);
        System.out.println(res);

         s = "458";
         int n = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            n = 10 * n + (c - '0');
        }
        System.out.println(n);

    }
}
