package com.shl.test;

import java.util.HashMap;
import java.util.Map;

/**
 * 滑动窗口
 *
 * @author shenhl
 */
public class SildeWindow {
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>(s.length());
        Map<Character, Integer> window = new HashMap<>(t.length());
        char[] chars = t.toCharArray();
        for (char c : chars) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        char[] sArr = s.toCharArray();
        int left = 0, right = 0;
        int valid = 0, len = Integer.MAX_VALUE, start = 0;
        while (right < sArr.length) {
            char c = sArr[right];
            // 扩大窗口
            right++;
            // 更新数据
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) {
                    valid++;
                }
            }

            while (valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - start;
                }
                char d = sArr[left];
                // 缩小窗口
                left++;
                // 数据更新
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
