package com.ljm.sort;

/**
 * @ClassName InsertSort
 * @Description 插入类型排序
 * @Author Jim
 * @Date 2022/4/4 9:39
 **/
public class InsertSort {

    public static void main(String[] args) {
        int[] nums = new int[]{5,2,4,6,9,-3};
        foldHalfInsert(nums);
        System.out.println(nums);
    }

    /**
     * @description 直接插入排序
     * 最好的情况下，递增（省去交换步骤，只遍历）时间:O(n) 空间:O(1)
     * 最坏情况下，递减（遍历 + 交换）时间:O(n2) 空间:O(1)
     * 稳定性：排序前后相同元素的相对位置不会改变
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/4 9:48
     **/
    public static void directInsert(int[] nums){
        for (int i = 1 ; i < nums.length ; i++){
            for (int j = i ; j > 0 ; j--){
                if (nums[j] < nums[j-1]){
                    // 交换
                    int temp = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = temp;
                }else{
                    break;
                }
            }
        }
    }

    /**
     * @description 折半插入排序
     * 时间:O(n * log2n) 空间:O(1)
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/4 9:50
     **/
    public static void foldHalfInsert(int[] nums){
        for (int i = 1 ; i < nums.length ; i++){
            int now = nums[i];
            int left = 0, right = i - 1;

            // 因为前面为已排序列，利用折半查找在已排序列中找到now位置
            // 这个“=”很关键
            while (left <= right){
                int mid = (left + right) / 2;
                if (now > nums[mid]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }

            // left或者right都是找到的位置，准备插入
            for (int j = i ; j > left ; j--){
                nums[j] = nums[j - 1];
            }
            nums[left] = now;
        }
    }

    /**
     * @description 希尔排序 （考察少）
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/4 11:25
     **/
    public static void shelSort(int[] nums){

    }
}
