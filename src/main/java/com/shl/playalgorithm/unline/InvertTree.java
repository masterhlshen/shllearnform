package com.shl.playalgorithm.unline;

/**
 * 226. 翻转二叉树
 * <a href="https://leetcode.cn/problems/invert-binary-tree/description/" />
 */
public class InvertTree {
    public TreeNode invertTree(TreeNode root) {

        if (root == null) {
            return root;
        }
        invertTree(root.left);
        invertTree(root.right);

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return root;
    }

    public TreeNode invertTree2(TreeNode root) {

        if (root == null) {
            return root;
        }

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;
        return root;
    }
}
