package com.andrew.algo

import java.util.*


/**
 * @author Andrew Tse
 * @date 2022/7/3
 * @description 双指针
 */
class DoublePointerTest {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    /**
     * eg: 合并两个有序链表
     * 链接: https://leetcode.cn/problems/merge-two-sorted-lists
     */
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        var temp1 = list1
        var temp2 = list2
        val dummy = ListNode(-1)
        var p = dummy

        while (temp1 != null && temp2 != null) {
            if (temp1.`val` < temp2.`val`) {
                p.next = temp1
                temp1 = temp1.next
            } else {
                p.next = temp2
                temp2 = temp2.next
            }
            p = p.next!!
        }
        if (temp1 != null) {
            p.next = temp1
        }
        if (temp2 != null) {
            p.next = temp2
        }
        return dummy.next
    }


    /**
     * eg: 分隔链表
     * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     * 你应当 保留 两个分区中每个节点的初始相对位置
     * 链接: https://leetcode.cn/problems/partition-list
     */
    fun partition(head: ListNode?, x: Int): ListNode? {
        val dummy1 = ListNode(-1)
        val dummy2 = ListNode(-1)

        var p1 = dummy1
        var p2 = dummy2

        var p = head
        while (p != null) {
            if (p.`val` < x) {
                p1.next = p
                p1 = p1.next!!
            } else {
                p2.next = p
                p2 = p2.next!!
            }
            val temp = p.next
            p.next = null
            p = temp
        }
        p1.next = dummy2.next
        return dummy1.next
    }

    /**
     * eg: 合并K个升序链表
     * 解法:
     * 1. 两两合并
     * 2. 利用最小堆不断获取最小节点
     * 链接: https://leetcode.cn/problems/merge-k-sorted-lists
     */
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        if (lists.isEmpty()) return null
        val dummy = ListNode(-1)
        var p = dummy

        // 使用最小堆存储节点
        val queue = PriorityQueue<ListNode>(lists.size) { o1, o2 ->
            o1.`val` - o2.`val`
        }

        for (listNode in lists) {
            if (listNode != null) {
                queue.add(listNode)
            }
        }

        while (!queue.isEmpty()) {
            val node = queue.poll()
            p.next = node
            p = p.next!!
            if (node.next != null) {
                queue.add(node.next)
            }
        }
        return dummy.next
    }

    /**
     * eg: 删除倒数第N个节点
     * 链接: https://leetcode.cn/problems/remove-nth-node-from-end-of-list
     */
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        if (head == null) return null
        val dummy = ListNode(-1)
        dummy.next = head
        var p1: ListNode? = dummy
        var p2: ListNode? = dummy
        for (i in 0..n) {
            p1 = p1?.next
        }

        while (p1 != null) {
            p1 = p1.next
            p2 = p2?.next
        }
        p2?.next = p2?.next?.next
        return dummy.next
    }

    /**
     * eg: 链表的中间节点
     * 解法: 快慢指针
     * 链接: https://leetcode.cn/problems/middle-of-the-linked-list
     */
    fun middleNode(head: ListNode?): ListNode? {
        head ?: return null
        var slow = head
        var fast = head
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }
        return slow
    }

    /**
     * eg: 判断链表是否有环
     * 解法: 快慢指针
     * 链接: https://leetcode.cn/problems/linked-list-cycle
     */
    fun hasCycle(head: ListNode?): Boolean {
        head ?: return false
        var slow = head
        var fast = head
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
            if (slow == fast) {
                return true
            }
        }
        return false
    }

    /**
     * eg: 环形链表的入口节点
     * 链接: https://leetcode.cn/problems/linked-list-cycle-ii
     */
    fun detectCycle(head: ListNode?): ListNode? {
        head ?: return null
        var slow = head
        var fast = head
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
            if (slow == fast) {
                break
            }
        }
        if (fast?.next == null) {
            return null
        }
        // 假设相遇点距离环的起点是m, slow指针走了k, fast指针走了2k, 那么head节点距离环的起点是k-m,
        // 而从相遇点再走k-m步也会到环的起点, 因此将任一指针指向head, 保持同步前进就会在环的起点相遇
        slow = head
        while (slow != fast) {
            slow = slow?.next
            fast = fast?.next
        }
        return slow
    }

    /**
     * eg: 相交链表
     * 链接: https://leetcode.cn/problems/intersection-of-two-linked-lists
     */
    fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
        headA ?: return null
        headB ?: return null
        var p1 = headA
        var p2 = headB
        while (p1 != p2) {
            p1 = if (p1 == null) {
                headB
            } else {
                p1.next
            }

            p2 = if (p2 == null) {
                headA
            } else {
                p2.next
            }
        }
        return p1
    }

    /**
     * eg: 交错字符串
     * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
     * 解法: 快慢指针合并两个链表的逆向. 需要用动态规划和双指针
     * 链接: https://leetcode.cn/problems/interleaving-string
     * 定义：计算 s1[i..] 和 s2[j..] 是否能组合出 s3[i+j..]
     */
    fun isInterleave1(s1: String, s2: String, s3: String): Boolean {
        val n1 = s1.length
        val n2 = s2.length
        val n3 = s3.length
        if (n1 + n2 != n3) {
            return false
        }
        val dp = Array(n1 + 1) { Array(n2 + 1) { false } }
        dp[0][0] = true
        for (i in 1..n1) {
            dp[i][0] = dp[i - 1][0] && s1[i - 1] == s3[i - 1]
        }
        for (j in 1..n2) {
            dp[0][j] = dp[0][j - 1] && s2[j - 1] == s3[j - 1]
        }
        for (i in 1..n1) {
            for (j in 1..n2) {
                // 当前位置是由[i-1,j]或[i,j-1], 和s1[i-1]或s2[j-1]组成的
                dp[i][j] = (dp[i - 1][j] && s1[i - 1] == s3[i + j - 1]) || (dp[i][j - 1] && s2[j - 1] == s3[i + j - 1])
            }
        }
        return dp[n1][n2]
    }

    private lateinit var memo: Array<Array<Int>>
    fun isInterleave2(s1: String, s2: String, s3: String): Boolean {
        val m = s1.length
        val n = s2.length
        val k = s3.length
        if (m + n != k) {
            return false
        }

        memo = Array(m + 1) { Array(n + 1) { -1 } }
        return dp(s1, 0, s2, 0, s3)
    }

    private fun dp(s1: String, i: Int, s2: String, j: Int, s3: String): Boolean {
        val k = i + j
        // base case
        if (k == s3.length) {
            return true
        }
        if (memo[i][j] != -1) {
            return memo[i][j] == 1
        }
        var res = false
        // 如果 s1[i] 可以匹配 s3[k]，那么填入 s1[i] 试一下
        if (i < s1.length && s1[i] == s3[k]) {
            res = res || dp(s1, i + 1, s2, j, s3)
        }
        // 如果 s2[j] 可以匹配 s3[k]，那么填入 s2[j] 试一下
        if (j < s2.length && s2[j] == s3[k]) {
            res = res || dp(s1, i, s2, j + 1, s3)
        }

        memo[i][j] = if (res) 1 else 0
        return res
    }

    /**
     * eg: 优势洗牌. 田忌赛马思想, 时间复杂度为二叉堆和排序 O(NlogN)
     * 链接: https://leetcode.cn/problems/advantage-shuffle/
     */
    fun advantageCount(nums1: IntArray, nums2: IntArray): IntArray {
        val n = nums1.size
        // nums2降序排序
        val priorityQueue = PriorityQueue { pair1: IntArray, pair2: IntArray ->
            pair2[1] - pair1[1]
        }
        for (i in 0 until n) {
            priorityQueue.offer(intArrayOf(i, nums2[i]))
        }
        // nums1升序排序
        Arrays.sort(nums1)

        var left = 0
        var right = n - 1
        val res = IntArray(n)
        while (priorityQueue.isNotEmpty()) {
            val pair = priorityQueue.poll()
            val index = pair[0]
            val maxVal = pair[1]
            if (maxVal < nums1[right]) {
                res[index] = nums1[right]
                right--
            } else {
                res[index] = nums1[left]
                left++
            }
        }
        return res
    }
}