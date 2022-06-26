package com.shl.tree;

public class TreeNode<T> {
    T val;
    TreeNode<T> left = null, right = null;

    public TreeNode(T val) {
        this.val = val;
    }

    public TreeNode() {

    }
}
