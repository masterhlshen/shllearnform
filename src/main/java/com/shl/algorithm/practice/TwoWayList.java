package com.shl.algorithm.practice;

/**
 * 双向链表
 * @author xc_002
 */
public class TwoWayList {

    public static void main(String[] args) {
        TwoWayList list = new TwoWayList();
        for (int i = 0; i < 100; i++) {
            list.insert(i);
        }
        list.remove(-1);
        list.transList();
    }

    public int remove(int val) {
        Node p = head;
        while (p.next != null && p.next.data != val) {
            p = p.next;
        }
        if (p.next != null) {
            p = p.next;
            if (p.next != null) {
                p.next.pre = p.pre;
                p.pre.next = p.next;
            } else {
                p.pre.next = null;
            }
            size--;
        }
        return val;
    }

    public int insert(int val) {
        Node node = new Node();
        node.data = val;
        if (last == null) {
            head.next = node;
            node.pre = head;
            last = node;
        } else {
            last.next = node;
            node.pre = last;
            last = node;
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

    public int size() {
        return size;
    }

    private static class Node {
        int data;
        Node pre;
        Node next;
    }

    private int size;
    private Node head = new Node();
    private Node last;
}
