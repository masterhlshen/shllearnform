package com.shl.algorithm.ag4.cp3;

public class SequentialSearchST<K, V>  {

    public void put(K k, V v) {
        for (Node x = first; x != null; x = x.next) {
            if (k.equals(x.k)) {
                x.v = v;
                return;
            }
        }
        first = new Node(k, v, first);
    }

    public V get(K k) {
        for (Node x = first; x != null; x = x.next) {
            if (k.equals(x.k)) {
                return x.v;
            }
        }
        return null;
    }

    private Node first;

    private class Node {
        K k;
        V v;
        Node next;

        public Node(K k, V v, Node next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }
    }
}
