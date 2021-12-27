package com.mid.base.dataStructure;

/**
 * 二分查找
 * https://www.cnblogs.com/kyoner/p/11080078.html
 */
public class BinarySearch {

    //数组值不重复，查找值下标
    int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;     // 注意

        while(left <= right) {           // 注意
            int mid = (right + left) / 2;
            if(nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;          // 注意
            else if (nums[mid] > target)
                right = mid - 1;         // 注意
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] intArray =  {1,2,3,4,5};
        int index = new BinarySearch().binarySearch(intArray,4);
        System.out.println("下标为："+index);
    }
}
