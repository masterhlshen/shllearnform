package com.shl.playalgorithm.linealg;

/**
 * 查找单链表的倒数第k个节点
 *
 */
public class FindFromEnd {

    public ListNode findFromEnd(ListNode list, int k) {
        ListNode p = list;
        for (int i = 0; i < k; i++) {
            p = p.next;
        }
        ListNode q = list;
        while (p != null) {
            q = q.next;
            p = p.next;
        }

        return q;
    }
}
