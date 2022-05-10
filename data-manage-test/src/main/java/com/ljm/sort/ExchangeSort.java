package com.ljm.sort;

/**
 * @ClassName ExchangeSort
 * @Description 交换类排序
 * @Author Jim
 * @Date 2022/4/4 11:29
 **/
public class ExchangeSort {
    public static void main(String[] args) {
        int[] nums = new int[]{5,2,4,6,9,-3};
        fastSort(nums, 0, nums.length-1);
        //bubbleSort(nums);
        System.out.println(nums);
    }

    /**
     * @description 冒泡排序
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/4 11:30
     **/
    public static void bubbleSort(int[] nums){
        boolean flag = false;
        for (int i = 0 ; i < nums.length - 1 ; i++){
            for (int j = 1 ; j < nums.length; j++){
                if (nums[j-1] > nums[j]){
                    // 交换
                    int temp = nums[j-1];
                    nums[j-1] = nums[j];
                    nums[j] = temp;
                    flag = true;
                }
            }

            if (!flag){
                break;
            }
        }
    }

    /**
     * @description 快速排序
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/4 11:40
     **/
    public static void fastSort(int[] nums, int left, int right){
        if (left >= right){
            return;
        }

        int base = nums[left];
        int i = left, j = right;

        while (i < j){
            while(i < j && nums[j] > base){
                j--;
            }

            while(i < j && nums[j] < base){
                i++;
            }

            if (i < j){
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            }
        }

        nums[left] = nums[i];
        nums[i] = base;
        fastSort(nums, left, right - 1);
        fastSort(nums, left + 1, right);
    }
}
