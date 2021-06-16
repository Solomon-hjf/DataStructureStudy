package com.hjfstudy.search;

public class InsertValueSearch {
    public static void main(String[] args) {
//        int[] arr = new int[100];
//        for (int i = 1; i < 100; i++) {
//            arr[i] = i;
//        }
        int arr[] = {1,8,10,89,1000,1000,1234};

        int res = insertValueSearch(arr, 0, arr.length - 1, 1000);
        System.out.println("res = " + res);
    }

    //编写插值查找算法
    //插值查找算法也要求数组是有序的

    /**
     * @param arr       传入的数组
     * @param left      左边的索引
     * @param right     右边的索引
     * @param findValue 查找值
     * @return 如果找到，就返回对应的下标，如果没有找到，就返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findValue) {
        System.out.println("调用依一次");

        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) * (findValue - left) / (arr[right] - arr[left]);
        int midValue = arr[mid];

        if (findValue > midValue) {//向右递归
            return insertValueSearch(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            return insertValueSearch(arr, left, mid - 1, findValue);
        }else {
            return mid;

        }
    }
}
