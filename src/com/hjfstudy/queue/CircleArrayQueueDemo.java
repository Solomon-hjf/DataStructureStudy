package com.hjfstudy.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        System.out.println("测试环形数组！");
        CircleArray queue = new CircleArray(4);//说明设置4，其队列的有效数据最大是3
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop){
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出队列");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列取出数据");
            System.out.println("h(head)：查看队列头数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try{
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':   //查看队列头的数据是什么？
                    try{
                        int res = queue.headQueue();
                        System.out.printf("取出队列头的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出！");
    }

}
class CircleArray{
    private int maxSize;
    private int front;
    //front变量的含义做一个调整：front就指向队列的第一个元素，也就是说arr[front]就是队列的第一个元素。front的初始值 = 0
    private int rear;
    //rear变量的含义做一个调整：rear指向队列的最后一个元素的后一个位置，因为希望空出一个空间做出一个约定。rear的初试值 = 0；
    private int[] arr;//存放数据

    public CircleArray(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满了
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    //判断是不是为空
    public boolean isEmpty(){
        return rear == front;
    }
    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否满
        if (isFull()){
            System.out.println("队列已满，不能加数据！");
            return;
        }
        //直接将数据加入
        arr[rear] = n;  //rear初始值就是0
        rear = (rear + 1) % maxSize;
    }
    //取出数据
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空，不能取数据!");
        }
        //这里需要先分析出front是指向队列的第一个元素
        //1、先把front对应的值保留到一个临时变量
        //2、将front后移,考虑取模
        //3、将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }
    //显示队列数据
    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空，不能取数据!");
        }
        //思路：从front开始遍历，遍历多少个元素
        for(int i = front; i < front + size();i++){
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
        }
    }

    //求出当前数组的有效数据的大小
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    //把头元素打出来
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空，没有数据！");
        }
        return arr[front];
    }
}