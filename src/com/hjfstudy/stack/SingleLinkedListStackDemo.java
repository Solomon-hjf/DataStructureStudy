package com.hjfstudy.stack;

import java.util.Scanner;

public class SingleLinkedListStackDemo {
    public static void main(String[] args) {
        //
        SingleLinkedListStack stack = new SingleLinkedListStack(5);
        Scanner scanner = new Scanner(System.in);
        String key = "";
        boolean loop = true;
        while (loop){
            System.out.println("show:表示显示栈");
            System.out.println("exit:表示退出");
            System.out.println("pop:弹栈");
            System.out.println("push:入栈");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key){
                case "show":
                    stack.show();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                case "pop":
                    try {
                        stack.pop();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case "push":
                    System.out.println("请输入一个节点");
                    int res = scanner.nextInt();
                    stack.push(res);
                default:
                    break;
            }
        }
//
//        stack.push(node1);
//        stack.push(node2);
//        stack.push(node3);
//        stack.push(node4);
//        stack.push(node5);
//        stack.show();
//        System.out.println("----------------------------------------");
//        stack.pop();
//        stack.pop();


    }
}

//定义一个栈
class SingleLinkedListStack {
    private int maxSize;
    private int top = -1;
    private Node head = new Node(0);
    private Node temp = head;
    Node temp1 = head;


    public SingleLinkedListStack(int maxSize) {
        this.maxSize = maxSize;
    }

    //判断栈空
    public boolean isEmpty() {
        return top < -1;
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //入栈
    public void push(int value) {
        Node newNode = new Node(value);
        if (isFull()) {//如果栈已满
            throw new RuntimeException("栈已满!");
        }
        if (head.next == null) {//如果栈空
            head.next = newNode;
        } else {
            temp.next = newNode;
        }
        temp = temp.next;
        top++;
    }

    //出栈
    public void pop() {
        Node cur = null;//指向倒数第二个
        if (isEmpty()) {
            //如果栈空
            throw new RuntimeException("栈已空，无法出栈！");
        }
        while (temp1.next != null) {
            cur = temp1;
            temp1 = temp1.next;
        }
        //退出循环的时候，cur指向倒数第二个，temp指向最后一个
        cur.next = temp1;
        System.out.println(cur.next);
        cur.next = null;
        temp1 = cur;
        top--;
    }

    //显示栈
    public void show(){
        Node temp = head;
        while (temp.next != null){
            System.out.println(temp.next);
            temp = temp.next;
        }
        return;
    }
}

class Node {
    //成员变量
    public int no;
    public Node next;

    //构造器
    public Node(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                '}';
    }
}
