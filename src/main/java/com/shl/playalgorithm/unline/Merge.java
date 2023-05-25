package com.shl.playalgorithm.unline;

import java.util.Arrays;

/**
 * 归并排序
 * 思想：把数组当成二叉树，后序，分治
 */
public class Merge {

    private static int[] temp;

    public static void sort(int[] nums) {
        temp = new int[nums.length];
        sort(nums, 0, nums.length - 1);
    }

    private static void sort(int[] nums, int lo, int hi) {
        if (lo == hi) {
            // 单个元素不排序
            return;
        }
        // 防止整型溢出
        int mid = lo + (hi - lo) / 2;

        // 左半部分
        sort(nums, lo, mid);
        sort(nums, mid + 1, hi);

        merge(nums, lo, mid, hi);
    }

    private static void merge(int[] nums, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            temp[i] = nums[i];
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

    public static void main(String[] args) {
        int[] nums = new int[]{10, 20, 1, 40, 20, 82, 2309, 234};
        Merge.sort(nums);
        System.out.println(Arrays.toString(nums));

        int[][] a = new int[0][0];
        System.out.println(a.length);

    }
}
