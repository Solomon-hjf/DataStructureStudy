package com.hjfstudy.recursion;

public class Queue8 {

    //先定义一个max表示共有多少个皇后
    //定义数组array，保存皇后位置的结果，比如 arr = {0,4,7,5,2,6,1,3;}
    int max = 8;
    int[] array = new int[max];
    static int count = 0;
    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("共进行了%d次",count);

    }

    //编写一个方法可以输出结果
    private void print() {
        for (int i = 0; i < array.length; i++) {

            System.out.print(array[i] + " ");
        }
        System.out.println();
    }


    //编写一个方法，放置第n个皇后
    private void check(int n) {
        if (n == max) {//当n等于8的时候，其实前8个皇后已经放好了
            count++;
            print();
            return;
        }
        //依次放置，并判断是不是冲突
        for (int i = 0; i < max; i++) {
            //先把当前的皇后n，放到该行的第一列
            array[n] = i;
            //判断当放置第n个皇后到i列的时候，是否冲突
            if (judge(n)){//不冲突
                //接着放n+1个皇后
                check(n+1);
            }
            //冲突，会回到上面的位置（array[n] = i）此时的i已经++了，也就是说往下一列放

        }
    }


    //编写一个方法判断是不是冲突

    /**
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //1、array[i] == array[n]表示n是不是和前面n-1个皇后在同一列
            //2、Math.abs(n-i) == Math.abs(array[n]-array[i])表示第n个皇后和前面n-1个皇后是不是在一条斜线上
            //相当于斜率是1
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }
}
