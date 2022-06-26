package com.shl.algorithm.ag4.cp2;

import com.shl.algorithm.ag4.util.StdRandom;
import com.shl.util.TimerCount;

import java.util.Random;

public class TestSort {
    public static void main(String[] args) {
        TestSort ts = new TestSort();
        TimerCount timerCount = new TimerCount();
        int size = 10000000;
        int[] data;
       /* int[] data = ts.createData(size);
        timerCount.start();
        new Selection().sort(data);
        timerCount.end("选择排序");*/
        // System.out.println(">>>sorted = " + Arrays.toString(data));
        /*System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
        data = ts.createData(size);
        timerCount.start();
        new Insertion().sort(data);
        timerCount.end("插入排序");*/
        // System.out.println(">>>sorted = " + Arrays.toString(data));

        data = ts.createData(size);
        // System.out.println(">>>" + Arrays.toString(data));
        timerCount.start();
        new Shell().sort(data);
        timerCount.end("希尔排序");
        // System.out.println(">>>" + Arrays.toString(data));

        data = ts.createData(size);
        // System.out.println(">>>" + Arrays.toString(data));
        timerCount.start();
        ts.sort(data);
        timerCount.end("自顶向下的归并排序");
        // System.out.println(">>>" + Arrays.toString(data));

        data = ts.createData(size);
        // System.out.println(">>>" + Arrays.toString(data));
        timerCount.start();
        ts.sortIn(data);
        timerCount.end("自顶向下的归并排序2");
        // System.out.println(">>>" + Arrays.toString(data));

        data = ts.createData(size);
        // System.out.println(">>>" + Arrays.toString(data));
        timerCount.start();
        ts.sortBU(data);
        timerCount.end("自底向上的归并排序");
        // System.out.println(">>>" + Arrays.toString(data));


        data = ts.createData(size);
        //  System.out.println(">>>" + Arrays.toString(data));
        timerCount.start();
        new Quick().sort(data);
        timerCount.end("快速排序");
        // System.out.println(">>>" + Arrays.toString(data));

    }

    static class Shell {
        void sort(int[] a) {
            int len = a.length;
            int h = 1;
            while (h < len / 3)
                h = 3 * h + 1;
            while (h >= 1) {
                for (int i = h; i < len; i++) {
                    for (int j = i; j >= h && (a[j] < a[j - h]); j -= h) {
                        int temp = a[j];
                        a[j] = a[j - h];
                        a[j - h] = temp;
                    }
                }
                h /= 3;
            }

        }
    }

    static class Insertion {
        void sort(int[] arr) {
            int len = arr.length;
            for (int i = 0; i < len; i++) {
                for (int j = i; j > 0 && (arr[j] < arr[j - 1]); j--) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }

    static class Selection {

        void sort(int[] arr) {
            int len = arr.length;
            int min;
            for (int i = 0; i < len; i++) {
                min = i;
                for (int j = i + 1; j < len; j++) {
                    if (arr[min] > arr[j]) {
                        int temp = arr[min];
                        arr[min] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }
    }

    int[] createData(int size) {
        int[] a = new int[size];
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < size; i++) {
            a[i] = r.nextInt(size);
        }

        return a;
    }

    int[] aux;

    void sort(int[] a) {
        aux = new int[a.length];
        sort(a, 0, a.length - 1);
    }

    void sort(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, hi, mid);
    }

    void merge(int[] a, int lo, int hi, int mid) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                // 左边用尽，取右边
                a[k] = aux[j++];
            } else if (j > hi) {
                // 右边用尽，取左边
                a[k] = aux[i++];
            } else if (aux[i] > aux[j]) {
                // 左边比右边大，取右边，右移
                a[k] = aux[j++];
            } else {
                // 取左边
                a[k] = aux[i++];
            }
        }
    }

    // ---------------------------归并排序改进
    // ---------------------------小规模的排序改成插入排序
    void sortIn(int[] a) {
        aux = new int[a.length];
        sortIn(a, 0, a.length - 1);
    }

    void sortIn(int[] a, int lo, int hi) {
        if (hi - lo < 15) {

            for (int i = lo; i <= hi; i++) {
                for (int j = i; j > lo && (a[j] < a[j - 1]); j--) {
                    int temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                }
            }
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sortIn(a, lo, mid);
        sortIn(a, mid + 1, hi);
        merge(a, lo, hi, mid);
    }


    // ---------------自底向上的归并排序
    void sortBU(int[] a) {
        int n = a.length;
        aux = new int[n];
        for (int sz = 1; sz < n; sz = sz << 1) {
            for (int lo = 0; lo < n - sz; lo += (sz << 1)) {
                merge(a, lo, Math.min(lo + (sz << 1) - 1, n - 1), lo + sz - 1);
            }
        }
    }

    static class Quick {
        public void sort(int[] a) {
            StdRandom.shuffle(a);
            sort(a, 0, a.length - 1);
        }

        private void sort(int[] a, int lo, int hi) {
            if (hi < (lo + 15)) {
                for (int i = lo; i <= hi; i++) {
                    for (int k = i; k > lo && (a[k - 1] > a[k]); k--) {
                        int temp = a[k];
                        a[k] = a[k - 1];
                        a[k - 1] = temp;
                    }
                }
                return;
            }
            int j = partition(a, lo, hi);
            sort(a, lo, j - 1);
            sort(a, j + 1, hi);
        }

        private int partition(int[] a, int lo, int hi) {
            int i = lo, j = hi + 1;
            int v = a[lo];
            while (true) {
                while ((a[++i] < v)) {
                    if (i == hi) {
                        break;
                    }
                }
                while ((a[--j] > v)) {
                    if (j == lo) {
                        break;
                    }
                }
                if (i >= j) break;
                int temp = a[j];
                a[j] = a[i];
                a[i] = temp;
            }
            a[lo] = a[j];
            a[j] = v;
            return j;
        }
    }




}
