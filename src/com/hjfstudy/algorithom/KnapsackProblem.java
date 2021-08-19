package com.hjfstudy.algorithom;

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};//物品的重量
        int[] val = {1500, 3000, 2000};//物品的价值
        int m = 4;//背包的容量
        int n = val.length;//物品的个数

        //创建二维数组
        //v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        int[][] path = new int[n + 1][m + 1];

        //初始化第一列
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        //初始化第一行
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }

        //根据前面的公式进行动态规划处理
        for (int i = 1; i < v.length; i++) {//不处理第一行
            for (int j = 1; j < v[0].length; j++) {//不处理第一列
                if (w[i - 1] > j) {//如果放入物品的重量大于背包容量
                    v[i][j] = v[i - 1][j];//就还放上一个物品
                } else {
//                    v[i][j] = Math.max(v[i-1][j],val[i-1] + v[i-1][j-w[i-1]]);
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        //为了记录商品存放到背包的问题
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    }else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        //输出一下表格
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }


        int i = path.length -1; //行的最大下标
        int j = path[0].length -1;//列的最大下标

        while (i > 0 && j > 0){//从path数组最后开始找
            if (path[i][j] == 1){
                System.out.printf("第%d个商品放入背包\n",i);
                j -= w[i-1];
            }
            i--;
        }

    }
}
