package com.shl.test;

import com.shl.util.JsonUtils;

import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().minAddToMakeValid("())))))))))"));
        int[] res = new Solution().nextGreaterElement(new int[]{2, 1, 2, 4, 3});
        System.out.println(JsonUtils.writeValueAsString(res));

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

    public int[] nextGreaterElement(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> s = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && s.peek() <= nums[i]) {
                s.pop();
            }
            res[i] = (s.isEmpty() ? -1 : s.peek());
            s.push(nums[i]);
        }
        return res;
    }

    public int[] nextGreaterElements(int[] nums) {

        return null;
    }
}
