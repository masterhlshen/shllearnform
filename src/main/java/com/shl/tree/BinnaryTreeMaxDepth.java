package com.shl.tree;

public class BinnaryTreeMaxDepth {

    public static void main(String[] args) {
        BinnaryTreeMaxDepth btmd = new BinnaryTreeMaxDepth();
        TreeNode<Integer> root = new TreeNode<>(20);
        root.left = new TreeNode<>(30);
        TreeNode<Integer> two = new TreeNode<>(40);
        root.right = two;
        two.right = new TreeNode<>(50);
        System.out.println(btmd.maxDepth(root));

        System.out.println(btmd.maxDepth2(root));
    }

    private int res = 0, depth = 0;

    // 前序解法
    public int maxDepth(TreeNode root) {
        tranverse(root);
        return res;
    }


    private void tranverse(TreeNode root) {
        if (root == null) return;

        // 前序位置
        depth++;
        if (root.left == null && root.right == null) {
            res = Math.max(res, depth);
        }
        tranverse(root.left);
        tranverse(root.right);

        // 后序位置
        depth--;

    }


    // 后序解法
    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;

        int left = maxDepth2(root.left);
        int right = maxDepth2(root.right);

        // 后序位置
        return Math.max(left, right) + 1 ;
    }

}
