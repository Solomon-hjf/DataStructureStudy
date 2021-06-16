package com.hjfstudy.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试
        //创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
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
                    System.out.println("输出一个数");
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
class ArrayQueue{
    private int maxSize;//表示队列的最大容量
    private int front;//指向队列头部的前一个位置
    private int rear;//指向队列的尾部，就是最后一个数据的位置
    private int[] arr;//用于存放数据，模拟队列

    //创建一个队列构造器
    public ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    //判断队列是否满了
    public boolean isFull(){
        return rear == maxSize -1;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }
    //向队列中添加数据
    public void addQueue(int n){
        if (isFull()){
            System.out.println("队列已经满了，无法添加");
            return;
        }
        rear++;
        arr[rear] = n;
    }
    //从队列中取出数据
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        front++;
        return arr[front];
    }

    //显示队列的所有数据
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列是空的，没有数据！");
            return;
        }
       for (int i =0;i < arr.length;i++){
           System.out.printf("arr[%d]=%d\n",i,arr[i]);
       }
    }
    //显示队列的头部数据，注意不是取数据
    public int headQueue(){
        //判断是不是空
        if(isEmpty()){
            throw new RuntimeException("队列是空的，没有数据！");
        }
        return arr[front+1];
    }
}
