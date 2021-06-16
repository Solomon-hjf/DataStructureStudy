package com.hjfstudy.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        insertSort(arr);
    }

    public static void insertSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int insertValue = arr[i];//要插入的值
            int insertIndex = i - 1;//插入的前一个值的索引
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                //把前一个后移
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //这里判断一下是不是需要赋值
            if (insertIndex + 1 != i){
                arr[insertIndex + 1] = insertValue;
            }

            System.out.println("第"+i+"轮排序后的结果为：");
            System.out.println(Arrays.toString(arr));
        }
        /**

         //第一轮
         int insertValue = arr[1];//要插入的值
         int insertIndex = 1 -1;//插入的前一个值的索引

         //当要插入的前一个值的索引大于等于0，并且前面的值大于要插入的值的时候，开始交换
         //因为前面的都是有序的
         while (insertIndex >= 0 && insertValue < arr[insertIndex]){
         //把前一个后移
         arr[insertIndex + 1] = arr[insertIndex];
         insertIndex--;
         }
         arr[insertIndex + 1] = insertValue;
         System.out.println("第一轮排序后的结果为：");
         System.out.println(Arrays.toString(arr));

         insertValue = arr[2];
         insertIndex = 2 -1;

         while (insertIndex >= 0 && insertValue < arr[insertIndex]){
         //把前一个后移
         arr[insertIndex + 1] = arr[insertIndex];
         insertIndex--;
         }
         arr[insertIndex + 1] = insertValue;
         System.out.println("第2轮排序后的结果为：");
         System.out.println(Arrays.toString(arr));



         insertValue = arr[3];
         insertIndex = 3 -1;

         while (insertIndex >= 0 && insertValue < arr[insertIndex]){
         //把前一个后移
         arr[insertIndex + 1] = arr[insertIndex];
         insertIndex--;
         }
         arr[insertIndex + 1] = insertValue;
         System.out.println("第3轮排序后的结果为：");
         System.out.println(Arrays.toString(arr));
         */
    }


}
