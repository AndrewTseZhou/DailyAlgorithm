package com.andrew.algo

import java.util.*

/**
 * @author Andrew Tse
 * @date 2022/10/5
 */
class QueueStackTest {
    /**
     * eg: 使括号有效的最少添加
     * 链接: https://leetcode.cn/problems/minimum-add-to-make-parentheses-valid/
     */
    fun minAddToMakeValid(s: String): Int {
        var left = 0
        var right = 0
        for (c in s) {
            if (c == '(') {
                left++
            } else if (c == ')') {
                if (left > 0) {
                    left--
                } else {
                    right++
                }
            }
        }
        return left + right
    }

    /**
     * eg: 平衡括号字符串的最少插入次数
     * 链接: https://leetcode.cn/problems/minimum-insertions-to-balance-a-parentheses-string/
     */
    fun minInsertions(s: String): Int {
        var left = 0
        var right = 0
        for (c in s) {
            if (c == '(') {
                right += 2
                if (right % 2 == 1) {
                    left++
                    right--
                }
            } else if (c == ')') {
                right--
                if (right == -1) {
                    left++
                    right = 1
                }
            }
        }
        return left + right
    }

    /**
     * eg: 下一个更大元素 O(n), 单调栈
     * 输入一个数组nums, 返回一个数组ans, ans[i]表示nums[i]右边第一个比nums[i]大的元素, 如果不存在, 则为-1
     */
    fun nextGreaterElementBasic(nums: IntArray): IntArray {
        val n = nums.size
        val res = IntArray(n)
        val stack = Stack<Int>()
        // 逆序入栈, 正序出栈
        for (i in n - 1 downTo 0) {
            while (stack.isNotEmpty() && stack.peek() <= nums[i]) {
                stack.pop()
            }
            res[i] = if (stack.isEmpty()) -1 else stack.peek()
            stack.push(nums[i])
        }
        return res
    }

    /**
     * eg: 下一个更大元素I, 单调栈
     * 链接: https://leetcode.cn/problems/next-greater-element-i/
     */
    fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
        val greater = nextGreaterElementBasic(nums2)
        val greaterMap = HashMap<Int, Int>()
        for (i in nums2.indices) {
            greaterMap[nums2[i]] = greater[i]
        }
        val res = IntArray(nums1.size)
        for (i in nums1.indices) {
            res[i] = greaterMap[nums1[i]] ?: -1
        }
        return res
    }

    /**
     * eg: 下一个更大元素II, 单调栈
     * 链接: https://leetcode.cn/problems/next-greater-element-ii/
     */
    fun nextGreaterElements(nums: IntArray): IntArray {
        val n = nums.size
        val res = IntArray(n)
        val stack = Stack<Int>()
        // 逆序入栈, 正序出栈, 使用数组翻倍和模运算来模拟循环数组
        for (i in 2 * n - 1 downTo 0) {
            while (stack.isNotEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop()
            }
            res[i % n] = if (stack.isEmpty()) -1 else stack.peek()
            stack.push(nums[i % n])
        }
        return res
    }

    /**
     * eg: 下一个更大元素 III, 单调栈
     * 链接: https://leetcode.cn/problems/next-greater-element-iii/
     */
    fun nextGreaterElement(n: Int): Int {
        val nums = n.toString().toCharArray()
        val size = nums.size
        var i = size - 2
        // 从右往左找到第一个非递增的位置
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--
        }
        // 如果不存在, 则返回-1
        if (i < 0) {
            return -1
        }
        var j = size - 1
        // 从右往左找到第一个比nums[i]大的位置
        while (j >= 0 && nums[j] <= nums[i]) {
            j--
        }
        // 交换nums[i]和nums[j]
        swap(nums, i, j)
        // 反转nums[i+1, n-1]
        reverse(nums, i + 1, size - 1)
        val res = String(nums).toLong()
        return if (res > Int.MAX_VALUE) -1 else res.toInt()
    }

    private fun swap(nums: CharArray, i: Int, j: Int) {
        val temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
    }

    private fun reverse(nums: CharArray, begin: Int, end: Int) {
        var i = begin
        var j = end
        while (i < j) {
            swap(nums, i, j)
            i++
            j--
        }
    }

    /**
     * eg: 每日温度, 单调栈
     * 链接: https://leetcode.cn/problems/daily-temperatures/
     */
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        val n = temperatures.size
        val res = IntArray(n)
        val stack = Stack<Int>()
        for (i in n - 1 downTo 0) {
            while (stack.isNotEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop()
            }
            res[i] = if (stack.isEmpty()) {
                0
            } else {
                stack.peek() - i
            }
            // 和nextGreaterElement不同, 这里是计算下标差值, 因此入栈的是下标
            stack.push(i)
        }
        return res
    }

    /**
     * eg: 去除重复字母, 单调栈
     * 链接: https://leetcode.cn/problems/remove-duplicate-letters/
     */
    fun removeDuplicateLetters(s: String): String {
        val stack = Stack<Char>()
        val inStack = BooleanArray(256)
        val count = IntArray(256)
        for (i in s.indices) {
            count[s[i].toInt()]++
        }
        for (c in s.toCharArray()) {
            count[c.toInt()]--
            if (inStack[c.toInt()]) continue
            while (stack.isNotEmpty() && stack.peek() > c) {
                // 如果栈顶元素在后面不再出现, 则不需要弹出
                if (count[stack.peek().toInt()] == 0) break
                inStack[stack.pop().toInt()] = false
            }
            stack.push(c)
            inStack[c.toInt()] = true
        }
        val sb = StringBuilder()
        while (stack.isNotEmpty()) {
            sb.append(stack.pop())
        }
        return sb.reverse().toString()
    }

    /**
     * eg: 滑动窗口最大值, 单调队列
     * 链接: https://leetcode.cn/problems/sliding-window-maximum/
     */
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        val n = nums.size
        val res = IntArray(n - k + 1)
        val deque = LinkedList<Int>()
        for (i in 0 until n) {
            // 保证队列头部是最大值
            while (deque.isNotEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast()
            }
            deque.offerLast(i)
            // 队列头部不在窗口内, 则移除
            if (deque.peekFirst() == i - k) {
                deque.pollFirst()
            }
            // 窗口长度达到k, 则记录最大值
            if (i >= k - 1) {
                res[i - k + 1] = nums[deque.peekFirst()]
            }
        }
        return res
    }
}