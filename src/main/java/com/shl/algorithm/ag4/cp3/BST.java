package com.shl.algorithm.ag4.cp3;

public class BST<K extends Comparable<K>, V> {

    public static void main(String[] args) {

    }

    public int size() {
        return root == null ? 0 : root.n;
    }

    public V get(K key) {
        Node node = get(root, key);
        return node == null ? null : node.value;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    public K min() {
        Node min = min(root);
        return min == null ? null : min.key;
    }

    public K floor(K key) {
        Node floor = floor(root, key);
        return floor == null ? null : floor.key;
    }

    public K select(int k) {
        Node select = select(root, k);
        return select == null ? null : select.key;
    }

    public int rank(K key) {
        return rank(key, root);
    }

    public K max() {
        Node max = max(root);
        return max == null ? null : max.key;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node x, K key) {
        if (x == null) {
            return x;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }

            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;

        }
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    private int rank(K key, Node x) {
        // 返回以x为根节点的子树中小
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(key, x.left);
        } else if (cmp > 0) {
            return 1 + size(x.left) + rank(key, x.right);
        }
        return size(x.left);
    }

    private Node select(Node x, int k) {
        if (x == null) {
            return null;
        }

        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);

        return x;
    }

    private Node floor(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cp = x.key.compareTo(key);
        if (cp == 0) {
            return x;
        } else if (cp < 0) {
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        }
        return x;
    }

    private Node get(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cp = x.key.compareTo(key);
        if (cp > 0) {
            return get(x.right, key);
        } else if (cp < 0) {
            return get(x.left, key);
        }
        return x;
    }

    private Node put(Node x, K key, V value) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        int cp = x.key.compareTo(key);
        if (cp > 0) {
            x.right = put(x.right, key, value);
        } else if (cp < 0) {
            x.left = put(x.left, key, value);
        } else {
            x.value = value;
        }
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node min(Node x) {
        if (x == null) return null;
        return min(x.left);
    }

    private Node max(Node x) {
        if (x == null) return null;
        return max(x.right);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.n;
    }

    private Node root;

    private class Node {
        K key;
        V value;
        int n;
        Node left, right;

        public Node(K key, V value, int n) {
            this.key = key;
            this.value = value;
            this.n = n;
        }
    }


}
