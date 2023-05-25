package com.shl.playalgorithm.linealg;

/**
 * 二分搜索
 */
public class BinarySearch {

    /**
     * 704. 二分查找
     * <a href="https://leetcode.cn/problems/binary-search/" />
     *
     * @param nums
     * @param target
     * @return
     */
    public int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            // left + (right - left) / 2 和 (left + right) / 2 效果是相同的，但是前一种写法可以有效防止溢出
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    public int binarySearchLeft(int[] nums, int target) {
        int left = 0, right = nums.length;
        // 左闭右开[left,right)
        while (left < right) {
            // left + (right - left) / 2 和 (left + right) / 2 效果是相同的，但是前一种写法可以有效防止溢出
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        if (left == nums.length) {
            return -1;
        }
        return nums[left] == target ? left : -1;
    }

    public int binarySearchLeftOpen(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        // 左闭右闭 [left, right]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid - 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }

        if (left == nums.length) {
            return -1;
        }
        return nums[left] == target ? left : -1;
    }

    public int binarySearchRight(int[] nums, int target) {
        int left = 0, right = nums.length;

        while (left < right) {
            // left + (right - left) / 2 和 (left + right) / 2 效果是相同的，但是前一种写法可以有效防止溢出
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        if ((left - 1) < 0) {
            return -1;
        }
        return nums[left - 1] == target ? left - 1 : -1;
    }

    public int binarySearchRightOpen(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        if ((left - 1) < 0) {
            return -1;
        }
        return nums[left - 1] == target ? (left - 1) : -1;
    }

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        int[] nums = new int[]{1, 2, 3, 3, 4, 5, 5, 6, 7, 9, 18};
        System.out.println(bs.binarySearch(nums, 8));
        System.out.println(bs.binarySearch(nums, 5));

        System.out.println(bs.binarySearchLeft(nums, 3));
        System.out.println(bs.binarySearchRight(nums, 3));

    }
}
