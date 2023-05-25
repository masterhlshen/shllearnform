package com.shl.playalgorithm.linealg;

import java.util.Arrays;

/**
 *移动零
 * <a href="https://leetcode.cn/problems/move-zeroes/" />
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow++] = nums[fast];
            }
            fast++;
        }
        while (slow < nums.length) {
            nums[slow++] = 0;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        MoveZeroes moveZeroes = new MoveZeroes();
        moveZeroes.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));

    }
}
