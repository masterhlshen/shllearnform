package com.shl.playalgorithm.unline;

public class TreeNodeUtil {

    public static void traverse(TreeNode root) {
        if (root == null) {
            System.out.print("#\t");
            return;
        }
        System.out.print(root.val + "\t");
        traverse(root.left);
        traverse(root.right);
    }
}
