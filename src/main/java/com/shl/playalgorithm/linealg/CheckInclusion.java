package com.shl.playalgorithm.linealg;

import java.util.HashMap;
import java.util.Map;

/**
 * 567. 字符串的排列
 * <a href="https://leetcode.cn/problems/permutation-in-string/" />
 */
public class CheckInclusion {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<>(s1.length());
        Map<Character, Integer> window = new HashMap<>(s1.length());

        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            if (need.containsKey(c)) {
                need.put(c, need.get(c) + 1);
            } else {
                need.put(c, 1);
            }
        }

        int left = 0, right = 0, v = 0;
        while (right < s2.length()) {

            char c = s2.charAt(right);
            right++;
            // 更新数据
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) {
                    v++;
                }
            }


            while ((right - left) >= s1.length()) {
                if (v == need.size()) {
                    return true;
                }
                char d = s2.charAt(left);
                left++;

                // 更新数据
                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.get(d))) {
                        v--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        String s1 = "ab", s2 = "eidbaooo";
        CheckInclusion checkInclusion = new CheckInclusion();
        boolean res = checkInclusion.checkInclusion(s1, s2);
        System.out.println(res);

    }
}
