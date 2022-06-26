package com.shl.algorithm.ag4.cp3;

public class BST<Key extends Comparable<Key>, Value> {

    public static void main(String[] args) {

        BST<String, String> bst = new BST<>();
        char[] a = "AAAAAAAAAAAAAAAAAAABBBBBBBBBBBBBBBBBCCCCCCCCCCCshenhl".toCharArray();
        for (char s : a) {
            bst.put(new String(new char[]{s}), "A");
        }
        System.out.println(bst.get("A"));
        System.out.println(bst.size());
    }

    public Value get(Key key) {
        return get(root, key);
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    public int size() {
        return size(root);
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


    private int size(Node x) {
        return x == null ? 0 : x.N;
    }

    private Node root;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        int N;

        public Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            N = n;
        }
    }
}
