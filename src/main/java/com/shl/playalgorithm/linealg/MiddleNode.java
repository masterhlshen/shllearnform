package com.shl.playalgorithm.linealg;

/**
 * 链表的中间结点
 * <a href="https://leetcode.cn/problems/middle-of-the-linked-list/" />
 */
public class MiddleNode {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode head = NodeUtil.init(10);
        ListNode mid = new MiddleNode().middleNode(head);
        System.out.println(mid.val);

    }
}
