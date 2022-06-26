package com.shl.algorithm.practice;

public class TwoWayCycleList {
    public static void main(String[] args) {
        TwoWayCycleList list = new TwoWayCycleList();
        for (int i = 0; i < 100; i++) {
            list.insert(i);
        }
        list.remove(0);
        System.out.println();
        System.out.println(list.size());
        list.remove(99);
        System.out.println();
        System.out.println(list.size());
        list.remove(100);
        list.transList();
        System.out.println();
        System.out.println(list.size());
    }

    public int remove(int val) {
        Node p = head;
        int i = 1;
        while (p.next != null && p.next.data != val && i <= size) {
            p = p.next;
            i++;
        }
        if (i == size) {

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
            node.next = head.next;
            last = node;
        } else {
            last.next = node;
            node.pre = last;
            last = node;
            last.next = head.next;
        }
        size++;
        return val;
    }

    public void transList() {
        Node p = head;
        int i = 1;
        while (p.next != null && i <=size) {
            p = p.next;
            i++;
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
