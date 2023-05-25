package com.shl.playalgorithm.unline;

import java.util.List;

public class CountSmaller {
    public List<Integer> countSmaller(int[] nums) {

        return null;
    }

    private void countSmaller(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        countSmaller(nums, lo, mid);
        countSmaller(nums, mid + 1, hi);

        count(nums, lo, mid, hi);
    }

    private void count(int[] nums, int lo, int mid, int hi) {

        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {

        }
    }
}
