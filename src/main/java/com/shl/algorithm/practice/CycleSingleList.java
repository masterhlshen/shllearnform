package com.shl.algorithm.practice;

public class CycleSingleList {

    public static void main(String[] args) {
        CycleSingleList list = new CycleSingleList();
        for (int i = 0; i < 100; i++) {
            list.insert(i);
        }
        list.transList();
    }

    public CycleSingleList() {
        head = new Node();
    }

    public void transList() {
        int i = 1;
        Node p = head;
        while (p.next != null && i <= (size + 1)) {
            p = p.next;
            System.out.print(p.data + "\t");
            i++;
        }
    }

    public int insert(int val) {
        Node n = new Node();
        n.data = val;
        if (last == null) {
            head.next = n;
            n.next = head.next;
        } else {
            last.next = n;
            n.next = head.next;
        }
        last = n;
        size++;
        return val;
    }

    public int size() {
        return size;
    }

    private int size;

    private static class Node {
        int data;
        Node next;
    }

    private Node head;
    private Node last;
}
