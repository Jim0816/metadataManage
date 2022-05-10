package com.ljm.test;

/**
 * @ClassName CodeTest
 * @Description
 * @Author Jim
 * @Date 2022/4/14 9:19
 **/
public class CodeTest {
    public static void main(String[] args) {
        int[] nums = new int[]{3,4,6,6,6,6,6,9,9,10};
        int index = findLeft(nums, 1);
        System.out.println(index);
    }

    private static int findLeft(int[] nums, int target){
        int left = 0, right = nums.length - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            if (target <= nums[mid]){
                right = mid - 1; // [left, mid - 1]
            }else if (target > nums[mid]){
                left = mid + 1; // [mid + 1, right]
            }
        }
        return left;
    }
}
