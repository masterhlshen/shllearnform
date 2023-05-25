package com.shl.playalgorithm.linealg;

import java.util.Arrays;

/**
 * 删除有序数组中的重复项
 * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-array/description/" />
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }

    public static void main(String[] args) {
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int k = removeDuplicates.removeDuplicates(nums);
        System.out.println(k);
        System.out.println(Arrays.toString(nums));
    }
}
