package com.andrew.algo

/**
 * @author Andrew Tse
 * @date 2022/7/17
 * @desc 滑动窗口
 */

// 滑动窗口框架
//fun slidingWindow(s: String, t: String) {
//    val need = mutableMapOf<Char, Int>()
//    val window = mutableMapOf<Char, Int>()
//
//    t.forEach {
//        need[it] = need.getOrDefault(it, 0) + 1
//    }
//
//    var left = 0
//    var right = 0
//    var valid = 0
//    while (right < s.length) {
//        // c 是将移入窗口的字符
//        val c = s[right]
//        // 增大窗口
//        right++
//        // 进行窗口内数据的更新
//        //...
//
//        // 判断左侧窗口是否要收缩
//        while (window needs shrink) {
//            // d是将移出窗口的字符
//            val d = s[left]
//            left++
//            // 进行窗口内数据的更新
//        }
//    }
//}

class SlidingWindowTest {
    /**
     * eg: 最小覆盖子串
     * 链接: https://leetcode.cn/problems/minimum-window-substring/
     */
    fun minWindow(s: String, t: String): String {
        val need = mutableMapOf<Char, Int>()
        val window = mutableMapOf<Char, Int>()
        t.forEach {
            need[it] = need.getOrDefault(it, 0) + 1
        }

        var left = 0
        var right = 0
        var valid = 0
        var start = 0
        var len = Int.MAX_VALUE

        while (right < s.length) {
            val c = s[right]
            right++
            if (need.containsKey(c)) {
                window[c] = window.getOrDefault(c, 0) + 1
                if (window[c]?.equals(need[c]) == true) {
                    valid++
                }
            }

            while (valid == need.size) {
                if (right - left < len) {
                    start = left
                    len = right - left
                }
                val d = s[left]
                left++
                if (need.containsKey(d)) {
                    if (window[d]?.equals(need[d]) == true) {
                        valid--
                    }
                    window[d] = window.getOrDefault(d, 0) - 1
                }
            }
        }
        return if (len == Int.MAX_VALUE) "" else s.substring(start, start + len)
    }

    /**
     * eg: 字符串的排列
     * 链接: https://leetcode.cn/problems/permutation-in-string/
     */
    fun checkInclusion(s1: String, s2: String): Boolean {
        val need = mutableMapOf<Char, Int>()
        val window = mutableMapOf<Char, Int>()
        s1.forEach {
            need[it] = need.getOrDefault(it, 0) + 1
        }

        var left = 0
        var right = 0
        var valid = 0

        while (right < s2.length) {
            val c = s2[right]
            right++
            if (need.containsKey(c)) {
                window[c] = window.getOrDefault(c, 0) + 1
                if (window[c]?.equals(need[c]) == true) {
                    valid++
                }
            }

            while (right - left >= s1.length) {
                if (valid == need.size) {
                    return true
                }
                val d = s2[left]
                left++
                if (need.containsKey(d)) {
                    if (window[d]?.equals(need[d]) == true) {
                        valid--
                    }
                    window[d] = window.getOrDefault(d, 0) - 1
                }
            }
        }
        return false
    }

    /**
     * eg: 找到字符串中所有字母异位词
     * 链接: https://leetcode.cn/problems/find-all-anagrams-in-a-string/
     */
    fun findAnagrams(s: String, p: String): List<Int> {
        val need = mutableMapOf<Char, Int>()
        val window = mutableMapOf<Char, Int>()

        p.forEach {
            need[it] = need.getOrDefault(it, 0) + 1
        }

        var left = 0
        var right = 0
        var valid = 0

        val indexList = mutableListOf<Int>()

        while (right < s.length) {
            val c = s[right]
            right++
            if (need.containsKey(c)) {
                window[c] = window.getOrDefault(c, 0) + 1
                if (window[c]?.equals(need[c]) == true) {
                    valid++
                }
            }

            while (right - left >= p.length) {
                if (valid == need.size) {
                    indexList.add(left)
                }
                val d = s[left]
                left++
                if (need.containsKey(d)) {
                    if (window[d]?.equals(need[d]) == true) {
                        valid--
                    }
                    window[d] = window.getOrDefault(d, 0) - 1
                }
            }
        }
        return indexList
    }

    /**
     * eg: 无重复字符的最长子串
     * 链接: https://leetcode.cn/problems/longest-substring-without-repeating-characters/
     */
    fun lengthOfLongestSubstring(s: String): Int {
        val window = mutableMapOf<Char, Int>()
        var left = 0
        var right = 0
        var max = 0
        while (right < s.length) {
            val c = s[right]
            if (window.containsKey(c)) {
                left = Math.max(left, window.getOrDefault(c, 0) + 1)
            }
            window[c] = right
            max = Math.max(max, right - left + 1)
            right++
        }
        return max
    }
}
