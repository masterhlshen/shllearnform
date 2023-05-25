package com.shl.playalgorithm.linealg;

/**
 * 构造链表数据
 */
public class NodeUtil {
    public static void trans(ListNode list) {
        while (list != null) {
            System.out.print(list.val + "\t");
            list = list.next;
        }

        System.out.println();
    }

    public static ListNode init(int num) {
        ListNode head = new ListNode(-1);
        ListNode p = head;
        for (int i = 0; i < num; i++) {
            ListNode e = new ListNode(i);
            p.next = e;
            p = p.next;
        }
        trans(head);
        return head;
    }

}
