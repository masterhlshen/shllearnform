package com.shl.playalgorithm.unline;

/**
 * 654. 最大二叉树
 * <a href = "https://leetcode.cn/problems/maximum-binary-tree/" />
 */
public class ConstructMaximumBinaryTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length - 1);
    }

    private TreeNode construct(int[] nums, int lo, int hi) {
        if (lo > hi) {
            return null;
        }

        int index = -1, maxVal = Integer.MIN_VALUE;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
                index = i;
            }
        }

        TreeNode root = new TreeNode(maxVal);
        TreeNode left = construct(nums, lo, index - 1);
        TreeNode right = construct(nums, index + 1, hi);

        root.left = left;
        root.right = right;
        return root;
    }
}
