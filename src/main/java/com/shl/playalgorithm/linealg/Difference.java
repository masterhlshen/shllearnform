package com.shl.playalgorithm.linealg;

import com.shl.util.JsonUtils;

/**
 * 差分数组
 */
public class Difference {

    private int[] diff;
    private int len;

    public Difference(int[] n) {
        len = n.length;
        diff = new int[len];
        diff[0] = n[0];
        for (int i = 1; i < len; i++) {
            diff[i] = n[i] - n[i - 1];
        }
    }

    public void increament(int i, int j, int val) {
        diff[i] += val;
        if ((j + 1) < len) {
            diff[j + 1] -= val;
        }
    }

    public int[] result() {
        int[] res = new int[len];
        res[0] = diff[0];
        for (int i = 1; i < len; i++) {
            res[i] = res[i - 1] + diff[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] n = new int[]{8, 5, 9, 6, 1};
        Difference difference = new Difference(n);
        difference.increament(1, 3, -10);
        System.out.println(JsonUtils.writeValueAsString(difference.result()));
    }
}
