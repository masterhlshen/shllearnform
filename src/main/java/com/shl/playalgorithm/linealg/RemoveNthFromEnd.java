package com.shl.playalgorithm.linealg;

/**
 * 删除链表的倒数第 N 个结点
 * <a href="https://leetcode.cn/problems/remove-nth-node-from-end-of-list/" />
 */
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode res = new ListNode(-1);
        res.next = head;
        ListNode p = res;
        n = n + 1;
        for (int i = 0; i < n; i++) {
            p = p.next;
        }
        ListNode q = res;
        while (p != null) {
            p = p.next;
            q = q.next;
        }
        if (q.next != null) {
            q.next = q.next.next;
        }
        return res.next;
    }



    public static void main(String[] args) {
        ListNode head = NodeUtil.init(10);
        RemoveNthFromEnd removeNthFromEnd = new RemoveNthFromEnd();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
        head = removeNthFromEnd.removeNthFromEnd(head, 1);
        NodeUtil.trans(head);
    }
}
