package com.shl.playalgorithm.linealg;

/**
 * 合并两个有序链表
 */
public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode p = list1, q = list2, r = head;
        while (p != null) {
            if (q != null) {
                if (q.val < p.val) {
                    r.next = q;
                    r = r.next;
                    q = q.next;
                    continue;
                }
            }
            r.next = p;
            r = r.next;
            p = p.next;
        }

        if (q != null) {
            r.next = q;
        }

        return head.next;
    }

    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode p = list1, q = list2, r = head;
        while (p != null && q != null) {
            if (p.val < q.val) {
                r.next = p;
                p = p.next;
            } else {
                r.next = q;
                q = q.next;
            }
            r = r.next;
        }

        if (p != null) {
            r.next = p;
        }

        if (q != null) {
            r.next = q;
        }

        return head.next;
    }

    public static void main(String[] args) {

    }
}


