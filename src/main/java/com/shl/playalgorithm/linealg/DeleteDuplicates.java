package com.shl.playalgorithm.linealg;

/**
 * 删除排序链表中的重复元素
 * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-list/" />
 */
public class DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            if (slow.val != fast.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        slow.next = fast;
        return head;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(1);
        ListNode c = new ListNode(1);
        ListNode d = new ListNode(2);
        ListNode e = new ListNode(2);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        DeleteDuplicates deleteDuplicates = new DeleteDuplicates();
        deleteDuplicates.deleteDuplicates(a);
        NodeUtil.trans(a);
    }
}
