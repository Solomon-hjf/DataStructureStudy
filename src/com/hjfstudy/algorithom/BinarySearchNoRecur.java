package com.hjfstudy.algorithom;

public class BinarySearchNoRecur {
    public static void main(String[] args) {
    //测试
        int arr[] = {1,3,8,10,11,67,100};
        int index = binarySearch(arr,110);
        System.out.println("index = " + index);
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left  + right)  /2;
            if (arr[mid] == target){
                return mid;//如果正好等于则返回
            }else if(arr[mid] > target){//如果中间值大于目标值
                right = mid - 1;//向左边找
            }else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
