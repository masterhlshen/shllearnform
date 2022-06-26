package com.shl.algorithm.ag4.cp2;

import java.util.Arrays;

public class MaxPQ {

    public static void main(String[] args) {
        MaxPQ pq = new MaxPQ(10);
        for (int i = 1; i <= 10; i++) {
            pq.insert(i);
        }
        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
        pq.insert(200);
        pq.insert(2000);
        pq.print();
    }

    MaxPQ() {
    }

    MaxPQ(int max) {
        pq = new int[max + 1];
    }

    MaxPQ(int[] a) {
        pq = a;
    }

    void insert(int v) {
        pq[++size] = v;
        swim(size);
    }

    int max() {
        if (!isEmpty()) {
            return pq[1];
        }
        return -1;
    }

    int delMax() {
        int v = pq[1];
        exch(1, size);
        pq[size--] = 0;
        sink(1);
        return v;
    }

    boolean isEmpty() {
        return size == 0;
    }

    int size() {

        return size;
    }

    void print() {
        System.out.println(Arrays.toString(pq));
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i] < pq[j];
    }

    private void exch(int i, int j) {
        int temp = pq[j];
        pq[j] = pq[i];
        pq[i] = temp;
    }

    private int[] pq;
    private int size;
}
