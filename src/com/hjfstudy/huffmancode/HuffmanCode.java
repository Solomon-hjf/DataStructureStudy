package com.hjfstudy.huffmancode;

import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        //构建字符串
        String content = "I like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length);

        List<Node> nodes = getNodes(contentBytes);
        System.out.println(nodes);

        //测试一下生成的二叉树
        System.out.println("赫夫曼树");
        Node huffmanTree = createHuffmanTree(nodes);
        System.out.println("前序遍历");
        preOrder(huffmanTree);
        //测试一把是否生成了对应的赫夫曼编码
        getCodes(huffmanTree);
        System.out.println("生成的赫夫曼编码表" + huffmanCodes);


    }


    //编写一个方法，将字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]

    /**
     * @param bytes        原始的字符串对应的byte数组
     * @param huffmanCodes 生成的赫夫曼编码
     * @return 返回赫夫曼编码处理后的一个byte数组
     * 举例：String content = "I like like like java do you like a java"; => byte[] contentBytes = content.getBytes();
     * 返回的字符串是"1010100010111111......."所对应的byte[] huffmanCodeByte,即8位对应一个byte
     * 放入到huffmanCodeByte[] 中
     * huffmanBodeBytes[0] = 10101000(补码) => byte [推导：10101000 => 10101000 - 1 => 10100111(反码)
     * => 11011000 -- >-88]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        //1、利用huffmanCode 将不有特殊 转成 赫夫曼编码对应的地字符串
        for (byte b : bytes) {
            String str = huffmanCodes.get(b);
            stringBuilder.append(str);
        }
        //确定编码后的长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length();
        } else {
            len = stringBuilder.length() + 1;
        }
        //创建数据压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {//不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte转成一个byte放到huffmanCoeBytes里
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte);
            index++;
        }
        return huffmanCodeBytes;
    }


    //生成赫夫曼树对应的赫夫曼编码
    //思路：
    //1、将赫夫曼编码表存放在Map<Byte,String>  形式
    //  32-> 01  97->100 100->1000 等等
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    //2、在生成赫夫曼编码表时，需要去拼接路径，创建一个StringBuilder 存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便，重载getCode
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //如果不为空，处理root左子树
        getCode(root.left, "0", stringBuilder);
        getCode(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 功能：将传入的Node结点的所有叶子结点的赫夫曼编码得到，并存放到huffmanCode集合中
     *
     * @param node          传入的结点
     * @param code          代表路径：左子节点是 0  右子节点是 1
     * @param stringBuilder 是用来拼接路径的
     */
    private static void getCode(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {//如果node == null不处理
            //判断当前的结点是叶子结点还是非叶子结点
            if (node.data == null) {//非叶子结点
                //递归处理
                //向左递归
                getCode(node.left, "0", stringBuilder2);
                //向右递归
                getCode(node.right, "1", stringBuilder2);
            } else {
                //说明是一个叶子结点
                //说明找到某个叶子结点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }

        }
    }

    //前序遍历的方法
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("根节点不能为空！");
        }
    }

    /**
     * 接收一个字节数组
     *
     * @param bytes 接收字节数组
     * @return 返回的是一个List形式
     */
    //获取节点
    public static List<Node> getNodes(byte[] bytes) {
        //1、创建一个ArrayLists
        ArrayList<Node> nodes = new ArrayList<>();

        //2、遍历bytes数组,统计每一个人byte出现的次数
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {//说明是第一次遍历
                //把这个数记作1
                counts.put(b, 1);
            } else {//这个数不是第一次出现
                counts.put(b, count + 1);//遇到一个byte就加一个
            }
        }
        //把每一个键值对转换成一个Node对象
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;

    }

    //通过一个List创建爱一个赫夫曼树
    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);//从小到大排序
            Node leftNode = nodes.get(0);//取出第一颗最小的二叉树
            Node rightNode = nodes.get(1);
            //创建一颗新的二叉树
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //将已经处理过的二叉树从数组中移除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树加入到节点当中，继续循环排序
            nodes.add(parent);
        }
        //循环结束以后，返回的只有一个节点,也就是根节点
        return nodes.get(0);
    }
}

//创建带权值的Node
class Node implements Comparable<Node> {
    Byte data;//存放数据（字符）本身，比如 'a' =>97 ' ' => 32
    int weight;//权值，表示字符出现的次数
    Node left;  //左节点
    Node right;//右节点

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
