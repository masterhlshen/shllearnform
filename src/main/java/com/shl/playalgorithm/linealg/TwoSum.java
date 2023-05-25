package com.shl.playalgorithm.linealg;

/**
 * 两数之和 II - 输入有序数组
 * <a href="" />
 */
public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return new int[]{-1, -1};
        }
        int len = numbers.length;
        int left = 0, right = len - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }
}
