package com.shl.playalgorithm.linealg;

/**
 * 分隔链表
 */
public class Partition {
    public ListNode partition(ListNode head, int x) {
        ListNode res = new ListNode(-1), r;
        ListNode res2 = new ListNode(-1), p;
        r = res;
        p = res2;
        while (head != null) {
            if (head.val < x) {
                r.next = head;
                r = r.next;
            } else {
                p.next = head;
                p = p.next;
            }
            ListNode n = head.next;
            head.next = null;
            head = n;
        }
        r.next = res2.next;
        return res.next;
    }

    public static void main(String[] args) {

    }
}
