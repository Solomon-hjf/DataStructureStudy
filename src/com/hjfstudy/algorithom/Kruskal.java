package com.hjfstudy.algorithom;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Arrays;

public class Kruskal {
    private int edgeNum;//边的个数
    private char[] vertex;//顶点数组
    private int[][] matrix;//邻接矩阵，表示顶点关系（权值）
    //用INF 表示两个顶点不能连通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {

        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //克鲁斯卡尔的邻接矩阵，表示边之间的关系
        //0表示自己和自己，INF表示不连通
        int[][] matrix = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 4, 5, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0},
        };

        //创建一个实例
        Kruskal kruskal = new Kruskal(vertexs, matrix);
        kruskal.print();

      EData[] edges =   kruskal.getEdges();
        //没有排序
        System.out.println("排序前 = " + Arrays.toString(edges));
        //排序后
        kruskal.sortEdges(edges);
        System.out.println("排序后 = " + Arrays.toString(edges));

        kruskal.Kruskal();

    }

    //构造器
    public Kruskal(char[] vertex, int[][] matrix) {
        //初始化顶点数和边的个数
        int vlen = vertex.length;

        //初始化顶点,采用的是复制拷贝的方式
        this.vertex = new char[vlen];
        for (int i = 0; i < vertex.length; i++) {
            this.vertex[i] = vertex[i];
        }

        //初始化边,使用的是复制拷贝的方式
        //二维矩阵的行和列就是顶点的个数
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计边
        //遍历这个邻接矩阵，拿掉里面不连通的边
        for (int i = 0; i < vlen; i++) {
            for (int j = i+1; j < vlen; j++) {//这里的i+1意思是自己和自己没有必要互相连接了
                if (this.matrix[i][j] != INF) {//有效边，也就是连着的
                    edgeNum++;
                }
            }
        }

    }

    //算法开始
    public void Kruskal(){
        int index = 0;//表示最后结果数组的索引，结果数组里有多少条边
        int[] ends = new int[edgeNum];//用于保存"已有最小生成树"中每个顶点在最小生成树中的终点
        //创建结果数组，保存最后的最小生成树
        EData[] rets = new EData[edgeNum];

        //获取图中所有边的集合
        EData[] edges = getEdges();

        //1、首先按照边的权值大小排序，从小到大
        sortEdges(edges);

        //遍历edges数组，将边添加到最小生成树，判断准备加入的边是否形成了回路，如果没有就加入
        for (int i = 0; i < edgeNum; i++) {//所有的边都获取一下
            //获取到第i条边的第一个顶点（起点）
            int p1 = getPosition(edges[i].start);
            //获取到第i条边的第二个顶点（终点）
            int p2 = getPosition(edges[i].end);

            //获取p1这个顶点在已有最小生成树中的终点
            int m = getEnd(ends,p1);
            //获取p2这个顶点在已有最小生成树中的终点
            int n = getEnd(ends,p2);

            //是否构成回路
            if (m !=n){//没有构成回路
                ends[m] = n;//设置m在"已有最小生成树中"中的终点
                rets[index++] = edges[i];//有一条边加入到结果里
            }
        }
        for (int i = 0; i < index; i++) {
             System.out.println("最小生成树为=" + rets[i]);
        }


    }


    //打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵为：");
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%12d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    //对边进行排序处理，冒泡
    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {//交换
                    EData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    //传入顶点的值，返回顶点的下标，如果找不到返回-1
    private int getPosition(char ch) {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 功能：获取图中的边，放到EData[]数组中，后面需要遍历该数组
     * 是通过matrix 邻接矩阵来获取
     * EData[] 形式：[['A','B',12],['B','F',7]]
     * @return
     */
    private EData[] getEdges(){
        int index = 0;
        EData[] edges = new EData[edgeNum];
        //遍历这个邻接矩阵
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i+1; j < vertex.length; j++) {//i+1跳过自己
                if (matrix[i][j] != INF){//如果这个边是有权值的，说明是有边的
                    //则把这个边放到新建的这个数组中去
                    edges[index++] = new EData(vertex[i],vertex[j],matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 功能：获取下标为i的顶点的终点，用于后面判断两个顶点的终点是否相同
     * @param ends 数组记录了各个顶点对应的终点是哪个？ ends数组是在遍历过程中，逐步形成的
     * @param i 传入的顶点对应的下标
     * @return 返回的就是 下标为i的这个顶点对应的终点的下标
     */
    //这里是难点
    private int getEnd(int[] ends,int i){
        while (ends[i] != 0){
            i = ends[i];
        }
        return i;
    }
}

//创建一个边类，它的对象实例表示一条边
//方便对边进行大小排序
class EData {
    char start;//边的一个点
    char end;//边的另外一个点
    int weight;//边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    //重新toString,便于输出
    @Override
    public String toString() {
        return "EData{" +
                "<" + start +
                ", " + end +
                ">=" + weight +
                '}';
    }
}