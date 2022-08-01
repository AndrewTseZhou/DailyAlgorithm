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
