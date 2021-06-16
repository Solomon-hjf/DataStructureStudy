package com.hjfstudy.tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int temp = 0;
        int[] arr = {4, 6, 8, 5, 9};
        int count = 1;
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
            System.out.println("第"+count+"次调整后的结果：" + Arrays.toString(arr));
            count++;
        }


        /**
         * 将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端
         * 重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
         */
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
        }
                System.out.println("数组等于：" + Arrays.toString(arr));
    }

    //编写一个堆排序的方法
    public static void heapSort(int[] arr) {
        System.out.println("堆排序！");

    }

    //将一个数组（二叉树），调整成一个大顶堆/
    /**
     * 功能：完成以i对应的非叶子节点的树调整成大顶堆
     * 举例：int arr[] = {4,6,8,5,9}; => i = 1 =>adjustHeap =>得到{4,9,8,5,6}
     * 如果再次调用 adjustHeap 传入的是 i = 0 => 得到{4,9,8,5,6} => {9,6,8,5,4}
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素继续调整，length是在逐渐的减少
     */
    public static void adjustHeap(int arr[], int i, int length) {
        int temp = arr[i];//先把i这个节点的数存到临时变量中

        //从第i个元素的左子节点开始找起,找到子节点中最大的
        for (int k = 2 * i + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {//说明左子节点的值小于右子节点
                k++;//k指向右子节点
            }
            if (arr[k] > temp) {//如果子节点的最大值大于父节点
                arr[i] = arr[k];//交换
                i = k;
            } else {
                break;
            }
        }
        //for循环后，我们已经将以i为父节点最大值，放在了最新（局部）
        arr[i] = temp;//将temp放到调整过后的位置
    }
}
