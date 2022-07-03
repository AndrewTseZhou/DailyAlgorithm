package com.andrew.algo

/**
 * @author Andrew Tse
 * @date 2022/7/3
 * @description 差分数组
 * 前缀和数组: 适用于原始数组不会被修改的情况下, 快速, 频繁计算一个索引区间内的元素之和
 * 差分数组: 适用于频繁对原始数组的某个区间的元素进行加减
 */
class Difference(nums: IntArray) {

    private var diff: IntArray = IntArray(nums.size)

    init {
        diff[0] = nums[0]
        for (i in 1 until nums.size) {
            diff[i] = nums[i] - nums[i - 1]
        }
    }

    fun increment(i: Int, j: Int, value: Int) {
        diff[i] += value
        if (j + 1 < diff.size) {
            diff[j + 1] -= value
        }
    }

    fun result(): IntArray {
        val res = IntArray(diff.size)
        res[0] = diff[0]
        for (i in 1 until diff.size) {
            res[i] = res[i - 1] + diff[i]
        }
        return res
    }
}

class DifferenceTest {
    /**
     * eg: 区间加法
     * 假设有一个长度为n的数组, 初始情况下所有数字均为0, 将会被给出k个更新的操作, 其中每个操作会被表示为一个三元组: [startIndex, endIndex, inc].
     * 需要将子数组A [startIndex ... endIndex]的值加上inc.
     */
    fun getModifiedArray1(length: Int, updates: Array<IntArray>): IntArray {
        val res = IntArray(length)
        for (update in updates) {
            res[update[0]] += update[2]
            if (update[1] + 1 < length) {
                res[update[1] + 1] -= update[2]
            }
        }
        for (i in 1 until length) {
            res[i] += res[i - 1]
        }
        return res
    }

    /**
     * eg: 区间加法
     */
    fun getModifiedArray2(length: Int, updates: Array<IntArray>): IntArray {
        val nums = IntArray(length)
        val df = Difference(nums)
        for (update in updates) {
            df.increment(update[0], update[1], update[2])
        }
        return df.result()
    }

    /**
     * eg: 航班预定统计
     * 这里有n个航班，它们分别从 1 到 n 进行编号。
     * 有一份航班预订表bookings ，表中第i条预订记录bookings[i] = [firsti, lasti, seatsi]意味着在从firsti到lasti（包含firsti和lasti）的每个航班上预订了seatsi个座位。
     * 请你返回一个长度为 n 的数组answer，里面的元素是每个航班预定的座位总数。
     * 链接：https://leetcode.cn/problems/corporate-flight-bookings
     */
    fun corpFlightBookings(bookings: Array<IntArray>, n: Int): IntArray {
        val nums = IntArray(n)
        val df = Difference(nums)
        for (booking in bookings) {
            // 编号从1开始, 要减1
            df.increment(booking[0] - 1, booking[1] - 1, booking[2])
        }
        return df.result()
    }

    /**
     * eg: 拼车
     * 车上最初有capacity个空座位。车只能向一个方向行驶（也就是说，不允许掉头或改变方向）
     * 给定整数capacity和一个数组 trips , trip[i] = [numPassengersi, fromi, toi]表示第 i 次旅行有numPassengersi乘客，接他们和放他们的位置分别是fromi和toi。这些位置是从汽车的初始位置向东的公里数。
     * 当且仅当你可以在所有给定的行程中接送所有乘客时，返回true，否则请返回 false。
     * 链接：https://leetcode.cn/problems/car-pooling
     */
    fun carPooling(trips: Array<IntArray>, capacity: Int): Boolean {
        val nums = IntArray(1001)
        val df = Difference(nums)
        for (trip in trips) {
            df.increment(trip[1], trip[2] - 1, trip[0])
        }

        val res = df.result()
        for (i in res) {
            if (i > capacity) {
                return false
            }
        }
        return true
    }
}