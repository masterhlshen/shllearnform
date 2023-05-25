package com.shl.playalgorithm.linealg;

import java.util.PriorityQueue;

/**
 * 合并K个升序链表
 * <a href="https://leetcode.cn/problems/merge-k-sorted-lists/" />
 */
public class MergeKLists {

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res = new ListNode(-1);
        ListNode p = res;
        // TODO PriorityQueue 这个堆实现，要好好研究下
        PriorityQueue<ListNode> queue = new PriorityQueue<>(
                lists.length, (a, b)->(a.val - b.val)
        );
        for (ListNode ele : lists) {
            if (ele != null) {
                queue.add(ele);
            }
        }
        while (!queue.isEmpty()) {
            ListNode e = queue.poll();
            p.next = e;
            if (e.next != null) {
                queue.add(e.next);
            }
            p = p.next;
        }
        return res.next;
    }

    public static void main(String[] args) {

    }
}
