package com.shl.algorithm.practice;

/**
 * LRU是Least Recently Used的缩写，即最近最少使用，是一种常用的页面置换算法，选择最近最久未使用的页面予以淘汰
 * 链表实现
 * @author xc_002
 */
public class SimpleLRU {

    public static void main(String[] args) {
        SimpleLRU list = new SimpleLRU();
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
        list.transList();
        for (int i = 3; i < 7; i++) {
            System.out.println();
            list.add(i);
            list.transList();
        }

    }

    public SimpleLRU() {
        this(5);
    }

    public SimpleLRU(int capcity) {
        this.capcity = capcity;
    }

    public int add(int val) {
        Node q = head;
        Node p = new Node();
        p.data = val;
        if (q == null) {
            head = p;
            last = head;
            size++;
        } else {
            Node k = null;
            while (q != null && q.data != val) {
                k = q;
                q = q.next;
            }
            p.next = head;
            head.pre = p;
            head = p;
            if (q == null) {
                if (size == capcity) {
                    k.pre.next = null;
                } else {
                    size++;
                }
            } else {
                q.pre.next = q.next;
                if (q.next != null) {
                    q.next.pre = q.pre;
                }
            }
        }
        return val;
    }

    public void transList() {
        Node p = head;
        while (p != null) {
            System.out.print(p.data + " \t");
            p = p.next;
        }
    }

    public int size() {
        return size;
    }

    private int capcity;
    private Node head, last;
    private int size;

    private static class Node {
        int data;
        Node next;
        Node pre;
    }


}
