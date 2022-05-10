package com.ljm.sort;

/**
 * @ClassName SelectSort
 * @Description 选择类型排序
 * @Author Jim
 * @Date 2022/4/5 10:19
 **/
public class SelectSort {
    public static void main(String[] args) {
        int[] nums = new int[]{1,6,5,4,3,-6};
        heapSort(nums);
        for (int i = 0 ; i < nums.length ; i++){
            System.out.print(nums[i] + ",");
        }
    }

    /**
     * @description 直接选择排序
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/5 10:20
     **/
    public static void directSelect(int[] nums){
        for (int i = 0 ; i < nums.length - 1 ; i++){
            // 从待排顺序中找出一个最小值
            int minValue = nums[i];
            int minIndex = i;
            for (int j = i + 1 ; j < nums.length ; j++){
                if (nums[j] < minValue){
                    minValue = nums[j];
                    minIndex = j;
                }
            }

            //交换
            int temp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = temp;
        }
    }

    /**
     * @description 堆排序
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/5 11:13
     **/
    public static void heapSort(int[] arr){
        // 1.创建堆
        for (int i = (arr.length -1) / 2 ; i >= 0 ; i--){
            adjustHeap(arr, i, arr.length - 1);
        }

        // 2.调整堆
        for (int i = arr.length - 1 ; i > 0 ; i--){
            // 将大根堆的根部arr[0] 最大值交换到数组最后
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;

            // 交换完后，大根堆被破坏，需要继续构建 将[0,i - 1]  length = i
            adjustHeap(arr, 0, i - 1);
        }
    }

    /**
     * @description 调整堆
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/6 10:29
     **/
    public static void adjustHeap(int[] arr, int parent, int end){
        int temp = arr[parent];
        int lChild = parent * 2 + 1;
        while (lChild <= end){
            // 右孩子
            int rChild = lChild + 1;
            // 比较左孩子、右孩子大小
            if (rChild < parent && arr[lChild] < arr[rChild]){
                lChild++;
            }
            // 此时, lChild为最大孩子
            if (temp >= arr[lChild]){
                break;
            }

            arr[parent] = arr[lChild];

            parent = lChild;
            lChild = parent * 2 + 1;
        }
        arr[parent] = temp;
    }
}
