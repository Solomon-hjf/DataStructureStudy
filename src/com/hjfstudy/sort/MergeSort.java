package com.hjfstudy.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];//归并排序需要一个额外的空间
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println("归并后的数组=" + Arrays.toString(arr));
    }

    //分+合的方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归
            mergeSort(arr,left,mid,temp);
            //向右递归
            mergeSort(arr,mid+1,right,temp);
            //合并
            merge(arr,left,mid,right,temp);
        }

    }

    /**
     * @param arr   原始的数组
     * @param left  最左边的索引值
     * @param mid   中间的索引值
     * @param right 右边的索引值
     * @param temp  临时数组
     */
    //合并方法
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //初始化索引
        int i = left;
        int j = right;
        int t = 0;//指向临时数组
        //一、
        //先把左右两边（有序）的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕
        while (i <= mid && j <= right) {
            //如果左边的数小，就把左边的拷贝到临时数组中
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {//如果右边的数小，就把右的拷贝到临时数组中
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        //二、
        //把有剩余数据的一边的数据依次全部填充到temp
        while (i < mid) {//如果左边的数据没有填充完，就全部依次填充
            temp[t] = arr[i];
            t = +1;
            i += 1;
        }

        while (j < right) {//如果右边的数据没有填充完，就全部依次填充
            temp[t] = arr[j];
            t = +1;
            j += 1;
        }
        //三、
        //将temp数组的元素拷贝到arr数组
        //注意，并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }
}
