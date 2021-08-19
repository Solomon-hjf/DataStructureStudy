package com.hjfstudy.algorithom;

import java.util.Arrays;

public class FloydAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix = new int[][]{
                {0, 5, 7, N, N, N, 2},
                {5, 0, N, 9, N, N, 3},
                {7, N, 0, N, 8, N, N},
                {N, 9, N, 0, N, 4, N},
                {N, N, 8, N, 0, 5, 4},
                {N, N, N, 4, 5, 0, 6},
                {2, 3, N, N, 4, 6, 0}
        };

        //创建图对象
        FGraph graph = new FGraph(vertex.length, matrix, vertex);
        graph.show();
        graph.floyd();
        graph.show();
    }
}

//创建图
class FGraph {
    private char[] vertex;//存放顶点数组
    private int[][] dis;//保存从各个顶点出发到其他顶点的距离，最后的结果也保留在该数组中
    private int[][] pre;//保存到达目标顶点的前驱顶点

    //构造器
    public FGraph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];

        //对pre数组初始化，存放的是前驱顶点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    //显示pre数组
    public void show() {

        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        for (int i = 0; i < dis.length; i++) {
            //先将pre数组输出的一行
            for (int j = 0; j < dis.length; j++) {
                System.out.print(vertex[pre[i][j]] + " ");
            }
            System.out.println();
            //输出dis数组的一行数据
            for (int j = 0; j < dis.length; j++) {
                System.out.print("(" + vertex[i] + "到" + vertex[i] + "的最短路径为" + dis[i][j] + ")");
            }
            System.out.println();
            System.out.println();
        }
    }

    //弗洛伊德算法开始
    public void floyd(){
        int len = 0;//遍历保存距离
        //对中间定点遍历，k就是中间顶点的下标['A', 'B', 'C', 'D', 'E', 'F', 'G']
        for (int k = 0; k < dis.length; k++) {
            //从i顶点开始出发['A', 'B', 'C', 'D', 'E', 'F', 'G']
            for (int i = 0; i < dis.length; i++) {
                //到达J这个顶点
                for (int j = 0; j < dis.length; j++) {
                    len= dis[i][k] + dis[k][j];//求持股从i出发到k，再从k到j顶点的距离
                    if (len < dis[i][j]){//如果len小于直连的距离，那么就更新
                        dis[i][j] = len;
                        pre[i][j] = pre[k][j];//更新前驱节点

                    }
                }
            }
        }
    }

}