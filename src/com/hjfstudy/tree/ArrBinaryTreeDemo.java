package com.hjfstudy.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        System.out.println("顺序存储");
        arrBinaryTree.preOrder();//1-2-4-5-3-6-7
        System.out.println("中序存储");
        arrBinaryTree.infixOrder();//4-2-5-1-6-3-7
        System.out.println("后序存储");
        arrBinaryTree.postOrder();//4-5-2-6-7-3-1
    }
}

class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }
    //重载
    public void preOrder() {
        this.preOrder(0);
    }

    public void infixOrder() {
        this.infixOrder(0);
    }

    public void postOrder(){
        this.postOrder(0);
    }

    //编写一个方法完成顺序存储二叉树
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向左递归遍历
        if (index * 2 + 1 < arr.length) {
            preOrder(index * 2 + 1);
        }
        //向右递归遍历
        if (index * 2 + 2 < arr.length) {
            preOrder(index * 2 + 2);
        }
    }

    //中序存储二叉树
    public void infixOrder(int index) {
        if (arr == null && arr.length == 0) {
            System.out.println("数组为空，不能遍历");
        }
        //向左递归遍历
        if (index * 2 + 1 < arr.length) {
            infixOrder(index * 2 + 1);
        }
        //输出当前节点
        System.out.println(arr[index]);
        //向右递归遍历
        if (index * 2 + 2 < arr.length) {
            infixOrder(index * 2 + 2);
        }
    }

    //后序存储二叉树
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("链表为空，无法存储");
        }
        //向左递归遍历
        if (index * 2 + 1 < arr.length) {
            postOrder(index * 2 + 1);
        }
        //向右递归
        if (index * 2 + 2 < arr.length) {
            postOrder(index * 2 + 2);
        }
        //输出当前的节点
        System.out.println(arr[index]);
    }
}