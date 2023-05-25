package com.shl.playalgorithm.linealg;

import java.util.Arrays;

/**
 *移除元素
 * <a href="https://leetcode.cn/problems/remove-element/" />
 */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 2, 3};
        RemoveElement removeElement = new RemoveElement();
        removeElement.removeElement(nums, 3);
        System.out.println(Arrays.toString(nums));
    }
}
