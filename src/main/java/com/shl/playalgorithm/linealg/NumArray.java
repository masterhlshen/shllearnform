package com.shl.playalgorithm.linealg;

/**
 * 前缀和
 */
public class NumArray {
    private int[] numArr;

    public NumArray(int[] nums) {
        numArr = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            numArr[i + 1] = nums[i] + numArr[i];
        }

    }

    public int sumRange(int left, int right) {
        return numArr[right + 1] - numArr[left];
    }

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(numArray.sumRange(0, 2));
        ;
        System.out.println(numArray.sumRange(2, 5));
        ;
        System.out.println(numArray.sumRange(0, 5));
        ;
    }
}
