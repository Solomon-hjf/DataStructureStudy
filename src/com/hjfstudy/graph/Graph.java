package com.hjfstudy.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    private ArrayList<String> vertexList; //存储顶点集合
    private int[][] edges;//存储图对应的邻接矩阵
    private int numOfEdges;//表示边的数目

    //定义给定数组boolean[],记录某个结点是否被访问
    private boolean[] isVisited;

    //得到第一个邻接结点的下标w
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 1) {
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接结点的下标来获取下一个邻接结点
    //v1 是前一个邻接结点， v2是当前结点
    public int getNextNeighbor(int v1,int v2) {
        for (int j = v2 +1;j < vertexList.size();j++){
            if (edges[v1][j] > 0){
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历
    //i 第一次就是0
    public void dfs(boolean[] isVisited,int i){
        //首先访问该结点
        System.out.print(getValueByIndex(i) + "->");
        //将结点设置为已经访问过
        isVisited[i] = true;

        //查找结点i的第一个邻接结点w
        int w = getFirstNeighbor(i);
        while (w != -1){//说明有邻接结点
            if (!isVisited[w]){//如果没有被访问过
                dfs(isVisited,w);
            }
            //如果w结点已经被访问过
            w = getNextNeighbor(i,w);
        }
    }

    //对dfs进行重载，遍历所有的结点，进行dfs
    public void dfs(){
        //遍历所有的结点进行dfs
        for (int i =0;i < getNumOfVertex();i++){
            if (!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    //对一个结点进行编写广度优先算法
    private void bfs(boolean[] isVisited,int i){
        int u; //表示队列的头结点对应的下标
        int w; //邻接结点w
        //队列，结点访问的顺序
        LinkedList queue = new LinkedList();
        //访问结点，输出结点信息
        System.out.print(getValueByIndex(i) + "=>");
        //标记为已访问
        isVisited[i] = true;
        //将结点加入队列
        queue.addLast(i);

        while ( !queue.isEmpty()){
            //取出队列头结点的下标
             u = (Integer) queue.removeFirst();
             //得到第一个邻接结点的下标w
             w = getFirstNeighbor(u);
             while (w != -1){//找到
                 //是否访问过
                 if (!isVisited[w]){
                     System.out.print(getValueByIndex(w) + "=>");
                     //标记已经访问过
                     isVisited[w] = true;
                     //入队
                     queue.addLast(w);
                 }
                 //以u为前驱结点，找w后面的下一个邻接点
                 //u是前一个结点，w是当前结点，方法找的是w后面的结点
                 w = getNextNeighbor(u,w);//体现出我们的广度优先
             }
        }
    }

    //遍历所有的结点，都进行广度优先
    public void bfs(){
        for (int i= 0;i < getNumOfVertex();i++){
            if (!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }



    //构造器
    public Graph(int n) {
        //初始化矩阵和vertex
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    //图中常用的方法
    //返回结点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    //得到边的个数
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回结点i（下标）对应的数据 0 -> "A"  1->"B"  2->"C"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1 和 v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //插入结点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //添加边

    /**
     * @param v1     表示点的下标即第几个顶点  "A"-"B"  "A"->0  "B"->1
     * @param v2     第二个顶点对应的下标
     * @param weight 表示权值
     */
    public void insertEdges(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }


    public static void main(String[] args) {
        //测试图创建是否oK

        int n = 5;
        String Vertexs[] = {"A", "B", "C", "D", "E"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环添加顶点
        for (String vertex : Vertexs) {
            graph.insertVertex(vertex);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        graph.insertEdges(0, 1, 1);
        graph.insertEdges(0, 2, 1);
        graph.insertEdges(1, 2, 1);
        graph.insertEdges(1, 3, 1);
        graph.insertEdges(1, 4, 1);

        //显示邻接矩阵
        graph.showGraph();

        //测试，
//        System.out.println("测试深度遍历");
//        graph.dfs();

        //测试广度优先
        System.out.println("测试广度优先");
        graph.bfs();
    }
}
