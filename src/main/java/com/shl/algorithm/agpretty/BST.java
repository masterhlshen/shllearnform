package com.shl.algorithm.agpretty;

public class BST<K extends Comparable, V> {

    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<>();
        for (int i = 65; i <= 125; i++) {
            String key = (char) i + "";
            // System.out.println(key);
            bst.put(key, i);
        }

        System.out.println(bst.size() + " " + (125 - 65 + 1));

        System.out.println(bst.get("A"));
        System.out.println((char) 125);
        System.out.println("max_key=" + bst.max());

        System.out.println("min_key=" + bst.min());

        System.out.println("floor_G" + bst.floor("G"));

    }

    private Node root;

    private class Node {

        private K key;
        private V value;
        private Node left, right;
        private int N;

        public Node(K key, V value, int n) {
            this.key = key;
            this.value = value;
            N = n;
        }
    }

    public int size() {
        return size(root);
    }

    public V get(K key) {
        return get(root, key);
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }


    public K max() {
        if (root == null) return null;
        Node p = root;
        while (p.right != null) {
            p = p.right;
        }
        return p.key;
    }

    public K min() {
        if (root == null) return null;
        Node p = root;
        while (p.left != null) {
            p = p.left;
        }
        return p.key;
    }

    public K floor(K key) {
        Node node = floor(root, key);
        return node != null ? node.key : null;
    }

    public K ceiling() {

        return null;
    }


    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.N;
    }

    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }
        int c = key.compareTo(node.key);
        if (c > 0) {
            return get(node.right, key);
        } else if (c < 0) {
            return get(node.left, key);
        }
        return node.value;
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value, 1);
        }

        int c = key.compareTo(node.key);
        if (c > 0) {
            node.right = put(node.right, key, value);
        } else if (c < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.value = value;
        }

        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node floor(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cp = key.compareTo(node.key);
        if (cp == 0) {
            return node;
        } else if (cp < 0) {
            return floor(node.left, key);
        }
        Node t = floor(node.right, key);
        if (t != null) {
            return t;
        }
        return node;
    }

    private Node ceiling(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cp = key.compareTo(node.key);
        if (cp == 0) {
            return node;
        }
        if (cp > 0) {
            return ceiling(node.right, key);
        }
        Node t = ceiling(node.left, key);
        if (t != null) {
            return t;
        }
        return node;
    }
}
