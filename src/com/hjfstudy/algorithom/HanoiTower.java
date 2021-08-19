package com.hjfstudy.algorithom;

public class HanoiTower {
    public static int count = 0;
    public static void main(String[] args) {

        hanoiTower(5,'A','B','C');
        System.out.println("一共走了 " + count + " 步");

    }

    public static void hanoiTower(int num, char a, char b, char c) {
        count++;
        //如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从" + a + "->" + c);
        } else {
            //如果有n >=2情况，我们总是可以看做是两个盘子：1、最下面的盘。2、上面的所有盘
            //1）先把最上面的盘 A->B
            hanoiTower(num - 1, a, c, b);
            //2）把最下面的盘A->C
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            //3）把B塔的所有盘从B -> C
            hanoiTower(num - 1, b, a, c);
        }
    }
}
