package com.shl.playalgorithm.linealg;

/**
 * 相交链表
 * <a href="https://leetcode.cn/problems/intersection-of-two-linked-lists/description/" />
 */
public class GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode  bp = headB;
        while (bp.next != null) {
            bp = bp.next;
        }

        bp.next = headA;

        ListNode slow = headB, fast = headB;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            bp.next = null;
            return null;
        }
        slow = headB;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        bp.next = null;
        return slow;
    }

    public ListNode getIntersectionNodeLine(ListNode headA, ListNode headB) {
        ListNode p = headA, q = headB;
        while (p != q) {
            if (p == null) {
                p = headB;
            } else {
                p = p.next;
            }
            if (q == null) {
                q = headA;
            } else {
                q = q.next;
            }
        }
        return p;
    }
}
