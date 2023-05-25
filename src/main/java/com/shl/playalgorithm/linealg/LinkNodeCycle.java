package com.shl.playalgorithm.linealg;

/**
 * 链表钟是否包含还
 * 快慢指针的运用
 */
public class LinkNodeCycle {

    /**
     * 判断链表中是否包含环
     * @param head
     * @return true / false
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            // 慢指针走一步
            slow = slow.next;
            // 快指针走两步
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 找出链表中环的起始节点
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return null;
        }

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = c;

        LinkNodeCycle linkNodeCycle = new LinkNodeCycle();

        ListNode node = linkNodeCycle.detectCycle(a);
        if (node == null) {
            System.out.println("没有环");
            return;
        }
        System.out.println(node.val);

        System.out.println(linkNodeCycle.hasCycle(a));
        ListNode p = node;
        while (p.next != node) {
            p = p.next;
        }
        p.next = null;
        System.out.println(linkNodeCycle.hasCycle(a));
        NodeUtil.trans(a);
    }
}
