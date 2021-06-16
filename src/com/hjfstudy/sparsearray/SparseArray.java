package com.hjfstudy.sparsearray;

public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组 11 * 11
        //0:表示没有棋子， 1表示黑子 2表示蓝字
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始的二维数组
        System.out.println("原始的二维数组");
        for(int[] row : chessArr1){
            for(int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //将二维数组转换成稀疏数组
        //1、先遍历二维数组，得到非零的数字个数
        int sum = 0;
        for (int i = 0; i < chessArr1.length;i++){
            for (int j = 0;j < chessArr1[0].length;j++){
               if (chessArr1[i][j] != 0){
                   sum++;
               }
            }
        }
        System.out.println("sum = " + sum);

        //创建对应的稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        //将二维数组的有效数据存入稀疏数组中
        //第一行要存行，列，非零值
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;

        int m = 1;
        //找出非零数组是第几行第几列，值是多少
        for (int i = 0; i < chessArr1.length;i++){
            for (int j = 0;j < chessArr1[0].length;j++){
                if (chessArr1[i][j] != 0){
                    sparseArr[m][0] = i;
                    sparseArr[m][1] = j;
                    sparseArr[m][2] = chessArr1[i][j];
                    m++;
                }
            }
        }
        //输出稀疏数组
        System.out.println("稀疏数组为：");
//        for (int i = 0; i < sparseArr.length; i++){
//            for (int j =0; j< sparseArr[0].length; j++){
//                System.out.printf("%d\t",sparseArr[i][j]);
//            }
//            System.out.println();
//        }
        for (int[] row : sparseArr){
            for (int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        System.out.println("-------------------------------------");
        //将稀疏数组恢复成原始的二维数组
        //设置转换后的二维数组的大小
        int row = sparseArr[0][0];
        int col = sparseArr[0][1];
        int chessArr2[][] = new int[row][col];

        //开始往里面放数据
        for (int h = 1; h < sparseArr[0].length;h++){
            int r = sparseArr[h][0];
            int c = sparseArr[h][1];
            chessArr2[r][c] = sparseArr[h][2];
        }
       //遍历二维数组
        System.out.println("----------稀疏数组转化为二维数组----------");
        for (int[] row3 : chessArr2){
            for (int data2 : row3){
                System.out.printf("%d\t",data2);
            }
            System.out.println();
        }


    }
}
