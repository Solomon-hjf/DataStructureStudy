package com.hjfstudy.search;

import java.util.ArrayList;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10,88,88,88,88,1000, 1234};
        ArrayList<Integer> integers = binarySearch2(arr, 0, arr.length-1, 88);

        System.out.println("res = " + integers);
    }

    //二分查找

    /**
     * @param arr     数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 要查找的数
     * @return 如果找到就返回索引，找不到就返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];

        if (findVal > midValue) {//向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midValue) {//向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }


    /**
     * 有序数组有相同值的时候
     * <p>
     * 思路分析：
     * 1、在找到mid 索引值，不要马上返回
     * 2、向mid索引值的左边扫描，将所有满足1000的元素的下标，加入到ArrayList
     * 3、向mid索引值的右边扫描，将所有满足1000的元素的下标，加入到ArrayList
     * 4、将ArrayList返回
     */
    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];

        if (findVal > midValue) {//向右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midValue) {//向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            ArrayList<Integer> resIndexList = new ArrayList<Integer>();
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {//退出
                    break;
                }
                resIndexList.add(temp);
                temp -= 1;  //temp左移
            }
            resIndexList.add(mid);

            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {//退出
                    break;
                }
                resIndexList.add(temp);
                temp += 1;
            }
            return resIndexList;
        }

    }

}
