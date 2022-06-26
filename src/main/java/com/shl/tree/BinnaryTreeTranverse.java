package com.shl.tree;

import com.shl.util.JsonUtils;

import java.util.LinkedList;
import java.util.List;

public class BinnaryTreeTranverse {
    public static void main(String[] args) {
        BinnaryTreeTranverse bttt = new BinnaryTreeTranverse();
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(30);
        TreeNode<Integer> two = new TreeNode<>(40);
        root.right = two;
        two.right = new TreeNode<>(50);
        List<Integer> res = bttt.tranverse(root);
        System.out.println(JsonUtils.writeValueAsString(res));
    }

    public List<Integer> tranverse(TreeNode<Integer> root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        res.addAll(tranverse(root.left));
        res.addAll(tranverse(root.right));
        res.add(root.val);
        return res;
    }
}
