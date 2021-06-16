package com.hjfstudy.sort;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = {3, 9, -1, 10, 20};
//        int[] arr = {1, 3, 5, 10, 20};

        int[] arr = new int[8];
        for (int i =0;i<arr.length-1;i++){
            arr[i] = (int)(Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是=" + format);
        //测试冒泡排序
        bubbleSort(arr);
        Date date2 = new Date();
        String format2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + format2);

    }


    public static void bubbleSort(int[] arr){
            int temp = 0;
            boolean flag = false;//标识变量，表示是否进行过交换
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = 0; j < arr.length - 1 - i; j++) {
                    if (arr[j] > arr[j + 1]) {
                        //进行交换了，把表示设为true
                        flag = true;
                        temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
//                System.out.println("第" + (i + 1) + "趟排序后的数组");
//                System.out.println(Arrays.toString(arr));

                if (!flag){//如果里面的循环走完了，都没有进行交换，说明里面已经拍好顺序了，直接退出
                    break;
                }else {//如果循环走完一趟，发现有交换，再把flag重置，继续执行
                    flag = false;
                }
            }
        }
}
