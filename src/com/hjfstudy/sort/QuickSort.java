package com.hjfstudy.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("arr = " + Arrays.toString(arr));
    }


    public static void quickSort(int[] arr, int left, int right) {
        int l = left;//左下标
        int r = right;//由下标

        //pivot 中轴值
        int pivot = arr[(left + right) / 2];
        int temp = 0;//交换时使用
        //while循环的目的是让比pivot值小的放到左边，
        //比pivot值大的放到右边
        while (l < r) {

            while (arr[l] < pivot) {
                //在pivot左边一直找，直到找到一个大于等于pivot的值
                l += 1;
            }
            while (arr[r] > pivot) {
                //在pivot右边一直找，直到找到一个小于等于pivot的值
                r -= 1;
            }
            //找到以后要干什么呢？交换
            if (l >= r) {//说明左右两边的值，已经按照左边小于等于pivot,右边全部是大于等于pivot的值
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完了发现arr[l] == pivot值相等 ，那么就--，前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完了发现arr[r] == pivot值相等 ，那么就--，前移
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left < r){
            quickSort(arr,left,r);
        }
        //向右递归
        if (l < right){
            quickSort(arr,l,right);
        }
    }
}
