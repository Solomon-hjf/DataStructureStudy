package com.hjfstudy.recursion;

public class MiGong {
    public static void main(String[] args) {
        //先创建一个地图
        //8行7列，1表示墙
        int[][] map = new int[8][7];
        //设置墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int j = 0; j < 8; j++) {
            map[j][0] = 1;
            map[j][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        System.out.println("---------------------------迷宫地图---------------------------");
        //打印墙（遍历）
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        setWay2(map,1,1);
        System.out.println("---------------------------小球找过路后的地图---------------------------");

        //打印墙（遍历）
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }


    }





    //使用递归回溯来给小球找路
    //说明:
    //1.map表示地图
    //2.i,j表示从地图的那个位置开始出发（i，j）
    //3.如果小球能到map[6][5]位置，则说明通路找到了
    //4、约定：当map[i][j]为0时，表示该点没有走过，当为1时表示墙，当为2时表示通路可以走，如果为3表示该位置已经走过，但是走不通
    //5.在走迷宫时，需要确定一个策略（方法） 下->右->上->左  如果走不通，再回溯。

    /**
     *
     * @param map 表示地图
     * @param i 从哪个位置开始找
     * @param j
     * @return 如果找到通路，就犯规true ，否则返回false
     */
    public static boolean setWay(int[][] map,int i,int j) {
        if (map[6][5] == 2) {//表示通路已经找到
            return true;
        } else {//通路没有找到，开始寻找
            if (map[i][j] == 0) {//如果当前的点还没有走过
                map[i][j] = 2; //先假定这个点可以走通
                if (setWay(map, i - 1, j)) {//向下走
                    return true;//如果能走通
                } else if (setWay(map, i, j + 1)) {//向右走
                    return true;
                } else if (setWay(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay(map, i, j - 1)) {//向左走
                    return true;
                }else {//说明该点是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    //修改找路策略
    public static boolean setWay2(int[][] map,int i,int j) {
        if (map[6][5] == 2) {//表示通路已经找到
            return true;
        } else {//通路没有找到，开始寻找
            if (map[i][j] == 0) {//如果当前的点还没有走过
                map[i][j] = 2; //先假定这个点可以走通
                if (setWay2(map, i - 1, j)) {//向上走
                    return true;//如果能走通
                } else if (setWay2(map, i, j + 1)) {//向右走
                    return true;
                } else if (setWay2(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay2(map, i, j - 1)) {//向左走
                    return true;
                }else {//说明该点是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
