package com.andrew.algo

/**
 * @author Andrew Tse
 * @date 2022/10/4
 */
class LinkedListTest {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    /**
     * eg: 反转链表, 递归解法
     * 链接: https://leetcode.cn/problems/reverse-linked-list/
     */
    fun reverseList(head: ListNode?): ListNode? {
        if (head?.next == null) {
            return head
        }
        val newHead = reverseList(head.next)
        head.next?.next = head
        head.next = null
        return newHead
    }

    /**
     * eg: 反转链表, 迭代解法
     * 链接: https://leetcode.cn/problems/reverse-linked-list/
     */
    fun reverseList2(head: ListNode?): ListNode? {
        var pre: ListNode? = null
        var cur = head
        while (cur != null) {
            val next = cur.next
            cur.next = pre
            pre = cur
            cur = next
        }
        return pre
    }

    /**
     * eg: 反转区间[a, b) 的元素, 左闭右开
     */
    fun reverseList(a: ListNode?, b: ListNode?): ListNode? {
        var pre: ListNode? = null
        var cur = a
        while (cur != b) {
            val next = cur?.next
            cur?.next = pre
            pre = cur
            cur = next
        }
        return pre
    }

    /**
     * eg: 反转链表前N个节点
     */
    private var successor: ListNode? = null
    fun reverseN(head: ListNode?, n: Int): ListNode? {
        if (n == 1) {
            successor = head?.next
            return head
        }
        val newHead = reverseN(head?.next, n - 1)
        head?.next?.next = head
        head?.next = successor
        return newHead
    }

    /**
     * eg: 反转链表的一部分
     * 链接: https://leetcode.cn/problems/reverse-linked-list-ii/
     */
    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        if (left == 1) {
            return reverseN(head, right)
        }
        head?.next = reverseBetween(head?.next, left - 1, right - 1)
        return head
    }

    /**
     * eg: K 个一组翻转链表
     * 链接: https://leetcode.cn/problems/reverse-nodes-in-k-group/
     */
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        head ?: return null
        var start = head
        var end = head
        for (i in 0 until k) {
            if (end == null) {
                return head
            }
            end = end.next
        }
        val newHead = reverseList(start, end)
        start.next = reverseKGroup(end, k)
        return newHead
    }
}