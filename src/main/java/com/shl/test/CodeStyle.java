package com.shl.test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CodeStyle {
    public static void main(String[] args) {

        int[] arr = new int[]{3, 6, 2, 12, 0, 434, 23, 53};
        Merge.sort(arr);
        System.out.println(Arrays.toString(arr));

    }


}

class Merge {

    private static int[] temp;

    public static void sort(int[] nums) {
        temp = new int[nums.length];
        sort(nums, 0, nums.length - 1);
    }

    private static void sort(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(nums, lo, mid);
        sort(nums, mid + 1, hi);

        merge(nums, lo, mid, hi);
    }

    private static void merge(int[] nums, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            temp[i] = nums[i];
        }

        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i == mid + 1) {
                nums[k] = temp[j++];
            } else if (j == hi + 1) {
                nums[k] = temp[i++];
            } else if (temp[i] > temp[j]) {
                nums[k] = temp[j++];
            } else {
                nums[k] = temp[i++];
            }
        }

    }

}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}


class SolutionCx {
    public Node connect(Node root) {
        if (root == null) return root;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node p = null;
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                Node n = q.poll();
                if (n.left != null) {
                    q.offer(n.left);
                }
                if (n.right != null) {
                    q.offer(n.right);
                }
                if (p == null) {
                    p = n;
                } else {
                    p.next = n;
                    p = n;
                }
            }
        }
        return root;
    }
}





