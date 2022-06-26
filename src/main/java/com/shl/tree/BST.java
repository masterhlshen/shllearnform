package com.shl.tree;

/**
 * 二叉查找树
 *
 * @param <Key>
 * @param <Value>
 */
public class BST<Key extends Comparable<Key>, Value> {

    public void tranvers() {
        tranvers(root);
    }


    public int size() {
        return size(root);
    }

    public Value get(Key key) {
        return get(root, key);
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private void tranvers(Node x) {
        if (x == null) {
            return;
        }
        System.out.println(x.val);
        tranvers(x.left);
        tranvers(x.right);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        }
        return x.val;
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.N;
    }

    private Node root;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        int N;

        Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }


    public static void main(String[] args) {
        BST<Integer, Integer> bst = new BST<>();
        bst.put(65, 65);
        bst.put(66, 66);
        bst.put(67, 67);
        bst.put(68, 68);
        bst.put(6, 9);
        bst.put(9, 9);
        bst.put(169, 169);
        bst.tranvers();
    }


}
