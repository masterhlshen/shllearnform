package com.shl.playalgorithm.linealg;

import java.util.*;

/**
 * 438. 找到字符串中所有字母异位词
 * <a href="https://leetcode.cn/problems/find-all-anagrams-in-a-string/" />
 */
public class FindAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new LinkedList<>();
        Map<Character, Integer> need = new HashMap<>(p.length());
        Map<Character, Integer> window = new HashMap<>(p.length());
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (need.containsKey(c)) {
                need.put(c, need.get(c) + 1);
            } else {
                need.put(c, 1);
            }
        }

        int left = 0, right = 0, v = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            // 更新数据
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) {
                    v++;
                }
            }

            while (v == need.size()) {
                if ((right - left) == p.length()) {
                    res.add(left);
                }


                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.get(d))) {
                        v--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FindAnagrams findAnagrams = new FindAnagrams();

        String s = "cbaebabacd", p = "abc";
        List<Integer> res = findAnagrams.findAnagrams(s, p);
        System.out.println(Arrays.toString(res.toArray()));

    }
}
