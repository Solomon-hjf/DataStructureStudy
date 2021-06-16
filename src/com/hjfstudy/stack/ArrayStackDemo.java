package com.hjfstudy.stack;

import java.util.Scanner;

public class
ArrayStackDemo {
    public static void main(String[] args) {
        //测试一把
        //先创建一个ArrayStack对象
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;//控制是否退出循环
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show:表示显示栈");
            System.out.println("exit:表示显示栈");
            System.out.println("pop:表示显示栈");
            System.out.println("push:表示显示栈");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是：%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
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

//定义一个ArrayStack 表示栈
class ArrayStack {
    private int maxSize;//栈的大小
    private int[] stack;//数组，模拟栈
    private int top = -1;//表示栈顶，初始化为-1
    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }
    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }
    //判断栈空
    public boolean isEmpty() {
        return top == -1;
    }
    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈已满，无法入栈！");
            return;
        }
        top++;
        stack[top] = value;
    }
    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，无法出栈！");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //显示栈的情况（遍历栈）
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据！");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }
}