package com.hjfstudy.tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }

    //编写一个前序遍历的方法
    public static void preOrder(Node root){
        if (root != null) {
            root.preOrder();
        }else {
            System.out.println("是空树，不能操作！");
        }
    }

    /**
     * @param arr 需要创建成赫夫曼树的数组
     * @return  创建好的赫夫曼树的root结点
     */
    //创建赫夫曼树的方法
    public static Node createHuffmanTree(int[] arr) {
        //第一步为了方便操作
        //1、遍历arr数组
        //2、将arr的每个元素构成一个Node
        //3、将Node放入到ArrayList当中
        ArrayList<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {
            //从小到大排序
            Collections.sort(nodes);
//            System.out.println("nodes = " + nodes);
            //取出根节点最小的两颗二叉树
            //1、取出权值最小的节点（二叉树）
            Node leftNode = nodes.get(0);
            //2、取出权值第二小的节点（二叉树）
            Node rightNode = nodes.get(1);
            //3、构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //4、从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //5、将新的二叉树的结点加入到链表
            nodes.add(parent);
        }
       return nodes.get(0);
    }
}

//创建节点类
//为了让Node对象持续排序Collections集合排序
//让Node 实现Comparable接口
class Node implements Comparable<Node> {
    int value;  //节点权值
    Node left;  //左节点
    Node right; //右节点

    //编写一个前序遍历
    public void preOrder( ) {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大排序
        return this.value - o.value;
    }
}