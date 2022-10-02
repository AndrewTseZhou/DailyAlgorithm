package com.andrew.algo

/**
 * @author Andrew Tse
 * @date 2022/9/29
 */
// 二分搜索框架
//    fun binarySearch(nums: IntArray, target: Int) {
//        var left = 0
//        var right = nums.size - 1
//        while (...) {
//            val mid = left + (right - left) / 2
//            if (nums[mid] == target) {
//               ...
//            } else if (nums[mid] < target) {
//                left = ...
//            } else {
//                right = ...
//            }
//        }
//    }

class BinarySearchTest {
    /**
     * eg: 二分查找
     * 链接: https://leetcode.cn/problems/binary-search/
     */
    fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1
        while (left <= right) {
            val mid = left + (right - left) / 2
            if (nums[mid] == target) {
                return mid
            } else if (nums[mid] < target) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return -1
    }

    /**
     * eg: 搜索左侧边界
     */
    fun leftSearch(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1
        while (left <= right) {
            val mid = left + (right - left) / 2
            if (nums[mid] == target) {
                right = mid - 1
            } else if (nums[mid] < target) {
                left = mid + 1
            } else if (nums[mid] > target) {
                right = mid - 1
            }
        }
        if (left >= nums.size || nums[left] != target) {
            return -1
        }
        return left
    }

    fun leftSearch2(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size
        while (left < right) {
            val mid = left + (right - left) / 2
            if (nums[mid] == target) {
                right = mid
            } else if (nums[mid] < target) {
                left = mid + 1
            } else if (nums[mid] > target) {
                right = mid
            }
        }
        return left
    }

    /**
     * eg: 搜索右侧边界
     */
    fun rightSearch(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1
        while (left <= right) {
            val mid = left + (right - left) / 2
            if (nums[mid] == target) {
                left = mid + 1
            } else if (nums[mid] < target) {
                left = mid + 1
            } else if (nums[mid] > target) {
                right = mid - 1
            }
        }
        if (right < 0 || nums[right] != target) {
            return -1
        }
        return right
    }

    fun rightSearch2(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size
        while (left < right) {
            val mid = left + (right - left) / 2
            if (nums[mid] == target) {
                left = mid + 1
            } else if (nums[mid] < target) {
                left = mid + 1
            } else if (nums[mid] > target) {
                right = mid
            }
        }
        // while 循环结束条件是left == right, 所以left - 1和right - 1都可以
        return right - 1
    }

    /**
     * eg: 搜索插入位置(搜索左侧边界算法的变形)
     * 链接: https://leetcode.cn/problems/search-insert-position/
     */
    fun searchInsert(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size
        while (left < right) {
            val mid = left + (right - left) / 2
            if (nums[mid] == target) {
                right = mid
            } else if (nums[mid] < target) {
                left = mid + 1
            } else if (nums[mid] > target) {
                right = mid
            }
        }
        return left
    }

    /**
     * eg: 搜索二维矩阵
     * 每行中的整数从左到右按升序排列。每行的第一个整数大于前一行的最后一个整数。
     * 二维数组的的行数 m 和列数 n，二维数组的坐标 (i, j) 可以映射成一维的 index = i * n + j;
     * 反过来也可以通过一维 index 反解出二维坐标 i = index / n, j = index % n
     * 链接: https://leetcode.cn/problems/search-a-2d-matrix/
     */
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        if (matrix.isEmpty() || matrix[0].isEmpty()) {
            return false
        }
        val m = matrix.size
        val n = matrix[0].size
        var left = 0
        var right = m * n - 1
        while (left <= right) {
            val mid = left + (right - left) / 2
            val midValue = matrix[mid / n][mid % n]
            if (midValue == target) {
                return true
            } else if (midValue < target) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return false
    }

    /**
     * eg: 搜索二维矩阵 II
     * 每行的元素从左到右升序排列。每列的元素从上到下升序排列。
     * 链接: https://leetcode.cn/problems/search-a-2d-matrix-ii/
     */
    fun searchMatrix2(matrix: Array<IntArray>, target: Int): Boolean {
        if (matrix.isEmpty() || matrix[0].isEmpty()) {
            return false
        }
        val m = matrix.size
        val n = matrix[0].size
        var i = 0
        var j = n - 1
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true
            } else if (matrix[i][j] < target) {
                i++
            } else if (matrix[i][j] > target) {
                j--
            }
        }
        return false
    }

    /**
     * eg:
     * 1. 山脉数组的峰顶索引
     * 2. 寻找峰值
     *
     * 链接:
     * 1. https://leetcode.cn/problems/peak-index-in-a-mountain-array/
     * 2. https://leetcode.cn/problems/find-peak-element/
     */
    fun peakIndexInMountainArray(arr: IntArray): Int {
        var left = 0
        var right = arr.size - 1
        // 题目必然有解, 所以left == right结束
        while (left < right) {
            val mid = left + (right - left) / 2
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return left
    }
}