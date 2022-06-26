package com.shl.test;

import java.util.Arrays;

public class CountRangeSum {

    public static void main(String[] args) {
        int i = new CountRangeSum().countRangeSum(new int[] {-2147483647,0,-2147483647,2147483647}, -564, 3864);
        System.out.println(i);
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
        // 构造前缀和
        int[] preSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[ i + 1] = preSum[i] + nums[i];
        }
        System.out.println(Arrays.toString(preSum));
        // 排序前缀和
        sort(preSum);
        System.out.println(Arrays.toString(preSum));

        return count;
    }

    private long lower, upper;
    private int[] temp;
    private int count = 0;
    private void sort(int[] nums) {
        temp = new int[nums.length];
        sort(nums, 0, nums.length - 1);
    }


    private void sort(int[] nums, int lo, int hi) {
        if (lo == hi) return;
        int mid = lo + (hi - lo) /2;
        sort(nums, lo, mid);
        sort(nums, mid + 1, hi);

        merge(nums, lo, mid, hi);
    }

    private void merge(int[] nums, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            temp[i] = nums[i];
        }

        int start = mid + 1, end = mid + 1;
        for (int i = lo; i <= mid; i++) {
            while (start <= hi && nums[start] - nums[i] < this.lower) {
                start++;
            }

            while (end <= hi && nums[end] - nums[i] <= this.upper) {
                end++;
            }
            count += (end - start);
        }

        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i == mid + 1) {
                nums[p] = temp[j++];
            } else if (j == hi + 1) {
                nums[p] = temp[i++];
            } else if (temp[i] > temp[j]) {
                nums[p] = temp[j++];
            } else {
                nums[p] = temp[i++];
            }
        }
    }
}
