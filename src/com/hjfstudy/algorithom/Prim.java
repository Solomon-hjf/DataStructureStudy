package com.hjfstudy.algorithom;

import java.util.Arrays;

public class Prim {
    public static void main(String[] args) {
        char[] data = {'A','B','C','D','E','F','G'};
        int vertx = data.length;
        //邻接矩阵的关系用二维数组表示
        int[][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,100000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000},
        };

        //创建图对象
        MGraph graph = new MGraph(vertx);
        MinTree minTree = new MinTree();
        minTree.createGraph(graph,vertx,data,weight);
//        minTree.showGraph(graph);
        minTree.prim(graph,0);
    }
}

class MinTree {
    public void createGraph(MGraph graph, int vertx, char data[], int[][] weight) {
        int i, j;
        for (i = 0; i < vertx; i++) {
            graph.data[i] = data[i];
            for (j = 0;j < vertx;j++){
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图
    public void showGraph(MGraph graph){
        for (int[] link : graph.weight){
            System.out.println(Arrays.toString(link));
        }
    }

    //编写prim算法，得到最小生成树

    /**
     * @param graph 图
     * @param v 表示从图的第几个顶点开始生成
     */
    public void prim(MGraph graph,int v){
        int[] visited = new int[graph.weight.length];

        //把当前这个结点标记为已访问
        visited[v] = 1;
        //h1 h2 记录两个顶点的下标
        int h1= -1;
        int h2= -1;
        int minWeight = 10000;

        for (int k = 1; k < graph.vertx; k++) {
            for (int i = 0; i < graph.vertx; i++) { //i结点表示已访问过的结点
                for (int j = 0; j < graph.vertx; j++) { //j结点表示未访问过的结点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j]< minWeight){
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到一条边是最小的
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值" + minWeight);
            //将当前结点标记为已访问
            visited[h2] = 1;
            minWeight = 10000;
        }
    }
}

class MGraph {
    int vertx;//表示图的节点的个数
    char[] data;//存放结点数据
    int[][] weight;//存放边，即邻接矩阵

    public MGraph(int vertx) {
        this.vertx = vertx;
        data = new char[vertx];
        weight = new int[vertx][vertx];
    }
}