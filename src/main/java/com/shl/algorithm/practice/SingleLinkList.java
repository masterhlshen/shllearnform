package com.shl.algorithm.practice;

/**
 * 单链表
 */
public class SingleLinkList {

    public static void main(String[] args) {
        SingleLinkList list = new SingleLinkList();
        for (int i = 0; i < 100; i++) {
            list.headInsert(i);
        }

        list.transList();
        System.out.println(list.size());

        System.out.println();
        // list.clear();
        for (int i = 0; i < 100; i++) {
            list.lastInsert(i);
        }

        list.transList();
        System.out.println(list.size());
    }

    /**
     * 头插法
     * @param val value
     * @return value
     */
    public int headInsert(int val) {
        Node p = new Node();
        p.data = val;
        p.next = head.next;
        head.next = p;
        if (last == null) {
            last = p;
        }
        size++;
        return val;
    }

    /**
     * 尾插法
     * @param val val
     * @return val
     */
    public int lastInsert(int val) {
        Node p = new Node();
        p.data = val;
        if (last == null) {
            p.next = head.next;
            head.next = p;
            last = p;
        } else {
            p.next = last.next;
            last.next = p;
            last = p;
        }
        size++;
        return val;
    }

    public void transList() {
        Node p = head;
        while (p.next != null) {
            p = p.next;
            System.out.print(p.data + "\t");
        }
    }

    private int size;

    public int size() {
        return size;
    }

    public void clear() {
        head.next = null;
        last = null;
        size = 0;
    }

    private static class Node {
        int data;
        Node next;
    }

    private Node head = new Node();
    private Node last;
}
