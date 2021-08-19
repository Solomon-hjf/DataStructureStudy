package com.hjfstudy.algorithom;

import java.util.Arrays;

public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix = new int[][]{
                {N, 5, 7, N, N, N, 2},
                {5, N, N, 9, N, N, 3},
                {7, N, N, N, 8, N, N},
                {N, 9, N, N, N, 4, N},
                {N, N, 8, N, N, 5, 4},
                {N, N, N, 4, 5, N, 6},
                {2, 3, N, N, 4, 6, N}
        };
        //创建Graph对象
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();

        graph.dsj(6);
        graph.showDjkstra();
    }
}

class Graph {
    private char[] vertex;//顶点数组
    private int[][] matrix;//邻接矩阵
    private VisitedVertex vv;//已经访问的顶点的集合

    //构造器
    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    //显示图
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    //显示结果
    public void showDjkstra() {
        vv.show();

    }
    //迪杰斯特拉算法

    /**
     * @param index 表示出发顶点对应的下标
     */
    public void dsj(int index) {
        vv = new VisitedVertex(vertex.length, index);//走完这步，说明从G(6)开始访问
        update(index);//更新index顶点到周围顶点的距离和前驱顶点（已更新两个数组，还剩一个）

        for (int j = 1; j < vertex.length; j++) {
            index = vv.updateArr();//选择并返回新的访问顶点（在这里更新第三个数组）
            update(index);
        }
    }

    //更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
    private void update(int index) {
        int len = 0;
        for (int i = 0; i < matrix.length; i++) {
            //len的含义：出发顶点到index顶点的距离 + 从index顶点到顶点i的距离之和
            len = vv.getDis(index) + matrix[index][i];
            //如果i顶点没有被访问过（!false），并且len小于出发顶点到i顶点的距离，就需要更新
            if (!vv.in(i) && len < vv.getDis(i)) {
                vv.updatePre(i, index);//更新i这个顶点的前驱为index
                vv.updateDis(i, len);//更新出发顶点到i顶点的距离;
            }
        }
    }
}

//已访问顶点的集合
class VisitedVertex {
    //记录各个顶点是否访问过 1表示访问过，0表示未访问，会动态更新
    public int[] already_arr;
    //每个下标对应的值为前一个顶点下标，会动态更新
    public int[] pre_visited;
    //记录出发顶点到其他所有顶点的距离，比如G为出发顶点，就会记录G到其他顶点的距离，会动态更新，求的最短距离就会存放到dis
    public int[] dis;

    /**
     * @param length 表示顶点的个数 初试length = 7
     * @param index  出发顶点的下标，比如G顶点，就是index = 6
     */
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //初始化dis数组
        Arrays.fill(dis, 65535);
        this.already_arr[index] = 1;
        this.dis[index] = 0;//设置出发顶点的访问距离为0
    }

    /**
     * 功能：判断index顶点是否被访问过
     *
     * @param index
     * @return 如果访问过，就返回true，如果没有访问过返回false
     */
    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    /**
     * 功能，更新出发顶点到index顶点的距离
     *
     * @param index 更新哪一个
     * @param len   距离
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 功能：更新pre这个顶点的前驱为index顶点
     *
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    /**
     * 功能：返回出发顶点到index顶点的距离
     *
     * @param index
     */
    public int getDis(int index) {
        return dis[index];
    }

    //继续选择并返回新的访问顶点，比如这里G访问完之后，就是A点作为新的访问顶点（注意不是出发顶点）
    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {//从访问结点数组中找到还没访问过，同时从出发顶点到这个顶点距离最小的那个
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        already_arr[index] = 1;
        return index;
    }

    //显示最后的结果
    //即将三个数组输出
    public void show() {
        System.out.println("======================");
        //输入alread_arr
        for (int i : already_arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("======================");
        //输入pre_visited
        for (int i : pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("======================");
        //输入alread_arr
        for (int i : dis) {
            System.out.print(i + " ");
        }
        System.out.println();
        //为了好看最后的最短距离
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertex[count] + "(" + i + ")");
            }else {
                System.out.println("N ");
            }
            count++;
        }
        System.out.println();
    }
}
