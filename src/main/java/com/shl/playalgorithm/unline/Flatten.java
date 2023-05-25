package com.shl.playalgorithm.unline;

/**
 * 114. 二叉树展开为链表
 * <a href = "https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/" />
 */
public class Flatten {



    // 遍历可解决问题
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;
        root.right = left;
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }



    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode a = new TreeNode(2);
        TreeNode b = new TreeNode(3);
        TreeNode c = new TreeNode(4);
        TreeNode e = new TreeNode(5);
        TreeNode f = new TreeNode(6);

        root.left = a;
        a.left = b;
        a.right = c;
        root.right = e;
        e.right = f;

        TreeNodeUtil.traverse(root);

        Flatten flatten = new Flatten();
        flatten.flatten(root);
        System.out.println();
        TreeNodeUtil.traverse(root);

    }
}
