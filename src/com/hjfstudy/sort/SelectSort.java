package com.hjfstudy.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1,6,-1,20,37,15};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        selectSort(arr);
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));

    }

    //选择排序
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {//一共进行了arr.length -1 轮
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {//每一轮又是一个循环
                if (min > arr[j]) {//如果最小的这个数大于后面的数，重置最小值
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {//其实min就相当于一个临时变量
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            //将最小值和当前比对的值进行交换

            System.out.println("第" + (i + 1) + "轮后");
            System.out.println(Arrays.toString(arr));
        }
    }
}
