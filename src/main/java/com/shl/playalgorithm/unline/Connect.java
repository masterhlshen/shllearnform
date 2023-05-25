package com.shl.playalgorithm.unline;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 116. 填充每个节点的下一个右侧节点指针
 * <a href = "https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/description/" />
 */
public class Connect {

    // 层序遍历
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int sz = queue.size();
            Node first = null;
            for (int i = 0; i < sz; i++) {
                Node n = queue.poll();

                if (first == null) {
                    first = n;
                } else {
                    first.next = n;
                    first = first.next;
                }

                if (n.left != null) {
                    queue.offer(n.left);
                }

                if (n.right != null) {
                    queue.offer(n.right);
                }
            }
        }
        return root;
    }

    // 完美二叉树抽象为三叉树
    public Node connect2(Node root) {
        if (root == null) {
            return root;
        }
        traverse(root.left, root.right);
        return root;
    }

    private void traverse(Node n1, Node n2) {
        if (n1 == null || n2 == null) {
            return;
        }

        n1.next = n2;

        traverse(n1.left, n1.right);
        traverse(n2.left, n2.right);
        traverse(n1.right, n2.left);
    }
}


class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

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
