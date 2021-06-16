package com.hjfstudy.sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);

    }

    public static void radixSort(int[] arr) {

        //先得到数组中最大的数的位数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大数的位是几位数
        int maxLength = (max + "").length();
        //定义一个二维数组，表示十个桶
        //说明：
        //1、二维数组包含10个一位数组
        //2、为了防止在放入数的时候，数据溢出，则每一个数组（桶）,大小为arr.length
        //3、明确：基数排序是适用空间换时间的经典排序
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际放了多少个数据，我们定义一个一位数组来记录各个桶每次放入的数据的个数
        //比如：bucketElementCounts[0],记录的就是bucket[0]桶的放入数据的个数
        int[] bucketElementCounts = new int[10];


        //开始循环
        for (int k = 0, n = 1; k < maxLength; k++, n *= 10) {//对大有几位就循环几次
            //第k轮，对个对应的数进行排序
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10;//   digitOfElement 每个元素的对应位数
                //第digitOfElement桶的第bucketElementCounts[digitOfElement]元素是arr[j]
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                //++的原因：如果下一次算出来的个位数还是digitOfElement，就让它放到下一个位置去
                bucketElementCounts[digitOfElement]++;//放进去以后放让存放
            }

            int index = 0;
            //依次把10个桶里面的数据放回到arr数组中
            for (int i = 0; i < bucketElementCounts.length; i++) {//遍历每一个桶
                if (bucketElementCounts[i] != 0) {
                    //从第一个桶开始
                    for (int j = 0; j < bucketElementCounts[i]; j++) {
                        arr[index++] = bucket[i][j];
                    }
                }
                //第一轮处理后，需要将bucketElementCounts[i] = 0 !!!!!
                bucketElementCounts[i] = 0;
            }
            System.out.println("遍历完"+(k+1)+"位数后的结果=" + Arrays.toString(arr));
        }
//
//
//        //第一轮，对个位数进行排序
//        for (int j = 0; j < arr.length; j++) {
//            int digitOfElement = arr[j] % 10;//   digitOfElement 每个元素的个位数
//            //第digitOfElement桶的第bucketElementCounts[digitOfElement]元素是arr[j]
//            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//            //++的原因：如果下一次算出来的个位数还是digitOfElement，就让它放到下一个位置去
//            bucketElementCounts[digitOfElement]++;//放进去以后放让存放
//        }
//
//        int index = 0;
//        //依次把10个桶里面的数据放回到arr数组中
//        for (int i = 0; i < bucketElementCounts.length; i++) {//遍历每一个桶
//            if (bucketElementCounts[i] != 0) {
//                //从第一个桶开始
//                for (int j = 0; j < bucketElementCounts[i]; j++) {
//                    arr[index++] = bucket[i][j];
//                }
//            }
//            //第一轮处理后，需要将bucketElementCounts[i] = 0 !!!!!
//            bucketElementCounts[i] = 0;
//        }
//        System.out.println("遍历完"+(k+1)+"位数后的结果=" + Arrays.toString(arr));


//        //第二轮，对十位数进行排序
//        //==============================================
//        for (int j = 0; j < arr.length; j++) {
//            int digitOfElement = arr[j] / 10 % 10;//   digitOfElement 每个元素的个位数
//            //第digitOfElement桶的第bucketElementCounts[digitOfElement]元素是arr[j]
//            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//            //++的原因：如果下一次算出来的个位数还是digitOfElement，就让它放到下一个位置去
//            bucketElementCounts[digitOfElement]++;//放进去以后放让存放
//        }
//        index = 0;
//        //依次把10个桶里面的数据放回到arr数组中
//        for (int i = 0; i < bucketElementCounts.length; i++) {//遍历每一个桶
//            if (bucketElementCounts[i] != 0) {
//                //从第一个桶开始
//                for (int j = 0; j < bucketElementCounts[i]; j++) {
//                    arr[index++] = bucket[i][j];
//                }
//            }
//            //第一轮处理后，需要将bucketElementCounts[i] = 0 !!!!!
//            bucketElementCounts[i] = 0;
//        }
//        System.out.println("遍历完十位数后的结果=" + Arrays.toString(arr));
//
//        //第三轮，对百位数进行排序
//        //==============================================
//        for (int j = 0; j < arr.length; j++) {
//            int digitOfElement = arr[j] / 100 % 10;//   digitOfElement 每个元素的个位数
//            //第digitOfElement桶的第bucketElementCounts[digitOfElement]元素是arr[j]
//            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//            //++的原因：如果下一次算出来的个位数还是digitOfElement，就让它放到下一个位置去
//            bucketElementCounts[digitOfElement]++;//放进去以后放让存放
//        }
//        index = 0;
//        //依次把10个桶里面的数据放回到arr数组中
//        for (int i = 0; i < bucketElementCounts.length; i++) {//遍历每一个桶
//            if (bucketElementCounts[i] != 0) {
//                //从第一个桶开始
//                for (int j = 0; j < bucketElementCounts[i]; j++) {
//                    arr[index++] = bucket[i][j];
//                }
//            }
//            //第一轮处理后，需要将bucketElementCounts[i] = 0 !!!!!
//            bucketElementCounts[i] = 0;
//        }
//        System.out.println("遍历完十位数后的结果=" + Arrays.toString(arr));
    }
}
