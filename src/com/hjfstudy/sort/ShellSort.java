package com.hjfstudy.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        shellSort(arr);
        shellSort2(arr);
    }

    public static void shellSort(int[] arr) {
        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {//分为5组
                for (int j = i - gap; j >= 0; j -= gap) {//遍历每一组的数据
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("第" + (++count) + "轮交换晚后的数据是");
            System.out.println(Arrays.toString(arr));
        }
        //第一轮排序


//        //第二轮排序，分为两组
//        temp = 0;
//        for (int i = 2; i < arr.length; i++) {//分为5组
//            for (int j = i - 2; j >= 0; j -= 2){//遍历每一组的数据
//                if (arr[j] > arr[j+2]){
//                    temp = arr[j];
//                    arr[j] = arr[j+2];
//                    arr[j+2] = temp;
//                }
//            }
//        }
//        System.out.println("第2轮交换晚后的数据是");
//        System.out.println(Arrays.toString(arr));
    }


    //对交换式的希尔排序进行优化--->移位法
    public static void shellSort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素开始，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j]<arr[j-gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]){
                      arr[j] = arr[j-gap];
                      j-=gap;
                    }
                    //当退出循环的时候，就给temp找到了放置的位置
                    arr[j] = temp;
                }
            }
        }
    }


}
