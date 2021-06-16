package com.hjfstudy.search;

import java.util.Arrays;
import java.util.jar.JarOutputStream;

public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int res = fibSearch(arr, 1000);
        System.out.println("res = " + res);
    }

    //因为后面要使用mid = low + F(k-1) -1 ,需要用到斐波那契数列，因此我们先获取到一个斐波那契数列
    //斐波那契数列中的每一个值都可以被黄金分割
    public static int[] fib() {
        int[] f = new int[maxSize];
        for (int i = 2; i < maxSize; i++) {
            f[0] = 1;
            f[1] = 1;
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //编写斐波那契查找算法、
    public static int fibSearch(int[] arr, int key) {
        int low = 0;//最左边的索引
        int high = arr.length - 1;//最右边的索引
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0;//存放要找的mid值
        int[] f = fib();//获取斐波那契数列
        //获取斐波那契分割数的下标，确定斐波那契数列的长度（ f[k] - 1）=>从斐波那契数列中取出一个合适的值
        while (high > f[k] - 1) {//顺序表长度不一定刚好等于F(k) -1，所以需要将原来的顺序表长度n增加至F(k)-1
            k++;//退出循环的时候，找到了合适的值，本例为k = ,f[1] = 8   8就是长度
        }
        //找到了合适的斐波那契数之后，按照这个数的长度，把arr补齐
        //因为f[k]值可能大于arr的长度，因此需要一个Arrays类，构造一个新的数组，并执行arr[]
        int[] temp = Arrays.copyOf(arr, f[k]);
        //实际上，需要用最后的一个数填充temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        //使用while循环来处理找到数key
        while (low <= high) {//这个条件成立，就会一直找下去
            mid = low + f[k - 1] - 1;//k--后跳回这里
            if (key < temp[mid]) {//向左递归
                high = mid - 1;
                //为什么是k--?
                //1、全部元素 = 前面的元素 + 后边的元素
                //f[k] = f[k-1] + f[k-2];
                //因为前面有f[k-1]个元素，所以可以继续拆分为f[k-1] = f[k-2] + f[k-3]
                //即在f[k-1]的前面继续查找k--
                //即下次循环 mid = f[k-1-1]-1
                k--;
            } else if (key > temp[mid]) {//向右递归
                low = mid + 1;
                k -= 2;
            }else {//找到key = mid
                if (mid <= high){
                    return mid;
                }else {
                    return high;//如果最后找到的mid在high的后面，也就是说这个值和原数组的high的值是一样的
                }
            }
        }
        return -1;
    }







}
