package com.shl.tree;

import java.util.LinkedList;
import java.util.Queue;

public class LevelTraverseTree {


    private void levelTraverse(TreeNode<Integer> root) {
        if (root == null) return;
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode<Integer> node = queue.poll();
                System.out.println(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
    }

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(30);
        TreeNode<Integer> two = new TreeNode<>(40);
        root.right = two;
        two.right = new TreeNode<>(50);
        LevelTraverseTree ltt = new LevelTraverseTree();
        ltt.levelTraverse(root);
    }
}
