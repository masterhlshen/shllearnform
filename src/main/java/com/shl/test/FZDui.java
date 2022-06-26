package com.shl.test;

import java.util.Arrays;

public class FZDui {
    public static void main(String[] args) {
        int i = new FZDui().reversePairs(new int[]{233, 2000000001, 234, 2000000006, 235, 2000000003, 236, 2000000007, 237, 2000000002, 2000000005, 233, 233, 233, 233, 233, 2000000004});
        System.out.println(i);

    }

    private int count = 0;
    private int[] temp;
    public int reversePairs(int[] nums) {
        temp = new int[nums.length];
        sort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));

        return count;
    }

    private void sort(int[] nums, int lo, int hi) {
        if (lo == hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(nums, lo, mid);
        sort(nums, mid + 1, hi);

        // 后序位置
        merge(nums, lo, mid, hi);
    }

    private void merge(int[] nums, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            temp[i] = nums[i];
        }

        int end = mid + 1;
        for (int i = lo; i <= mid; i++) {
            while ((end <= hi) && ((long)temp[i] > ((long)temp[end] * 2))) {
                end++;
            }
            count += (end - (mid + 1));
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
