package com.shl.playalgorithm.linealg;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 * <a href="https://leetcode.cn/problems/minimum-window-substring/" />
 */
public class MinWindow {
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>(t.length());
        Map<Character, Integer> window = new HashMap<>(t.length());
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (need.containsKey(c)) {
                need.put(c, need.get(c) + 1);
            } else {
                need.put(c, 1);
            }
        }
        int left = 0, right = 0, start = 0;
        int v = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            // 移入窗口的数据
            char c = s.charAt(right);
            // 增大窗口
            right++;
            // 窗口内的数据更新
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) {
                    v++;
                }
            }

            while (v == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // 移出窗口的数据
                char d = s.charAt(left);
                // 缩小窗口
                left++;
                // 窗口内的数据更新
                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.get(d))) {
                        v--;
                    }
                    window.put(d, window.get(d) - 1);
                }

            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        MinWindow minWindow = new MinWindow();
        String res = minWindow.minWindow(s, t);
        System.out.println(res);
    }
}
