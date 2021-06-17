package com.hjfstudy.huffmancode;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        /**
        //构建字符串
        String content = " like like like java do you like a java";
        byte[] contentBytes = content.getBytes();

        System.out.println("压缩后的长度是" + contentBytes.length);
        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的结果是：" + Arrays.toString(huffmanCodeBytes));
        System.out.println("压缩后的长度是" + huffmanCodeBytes.length);

//        List<Node> nodes = getNodes(contentBytes);
//        List<Node> nodes = getNodes(contentBytes);
//        System.out.println(nodes);
//
//        //测试一下生成的二叉树
//        System.out.println("赫夫曼树");
//        Node huffmanTree = createHuffmanTree(nodes);
//        System.out.println("前序遍历");
//        preOrder(huffmanTree);
//        //测试一把是否生成了对应的赫夫曼编码
//        getCodes(huffmanTree);
//        System.out.println("生成的赫夫曼编码表" + huffmanCodes);
//
//        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
//        System.out.println("huffmanCodeBytes = " + Arrays.toString(huffmanCodeBytes));

        byte[] sourceBytes = deCode(huffmanCodes, huffmanCodeBytes);
        System.out.println("原来的字符串的 = " + new String(sourceBytes));
**/
        //测试压缩文件的代码
//        String srcFile = "/Users/mac/Desktop/吉他谱/src.jpg";
//        String dstFile = "/Users/mac/Desktop/吉他谱/des.zip";
//        zipFile(srcFile,dstFile);
//        System.out.println("压缩文件成功");

        //测试解压文件
        String zipFile = "/Users/mac/Desktop/吉他谱/des.zip";
        String dstFile = "/Users/mac/Desktop/吉他谱/src2.jpg";
        unZip(zipFile,dstFile);
        System.out.println("解压成功！");
    }
    //编写一个方法：将一个文件压缩
    public static void zipFile(String srcFile,String dstFile) {
        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件输入流
        FileInputStream is = null;
        try {
            //创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件一样大小的数组
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流，存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //这里以对象流的方式写入赫夫曼编码，是为了以后恢复源数据
            //注意一定要把赫夫曼编码写入压缩文件
            oos.writeObject(huffmanCodes);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                is.close();
                os.close();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //完成解压文件的方法

    /**
     * @param zipFile 需要解压的文件
     * @param dstFile 解压到的路径
     */
    public static void unZip(String zipFile,String dstFile){
        //定义文件的输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try{
            //创建文件输入流
           is =  new FileInputStream(zipFile);
           //创建一个和 is相关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte[]数组和huffmanBytes
            byte[] huffmanBytes = (byte[])ois.readObject();
            //读取赫夫曼编码表
            Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject();

            //解码
            byte[] bytes = deCode(huffmanCodes, huffmanBytes);
            //将bytes写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到file文件
            os.write(bytes);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                ois.close();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }






    //完成数据的解压
    //思路：
    //1、将HuffmanCodeBytes  [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //重新先转成赫夫曼编码对应的二进制字符串
    //2、将赫夫曼编码对应的二进制字符串 对照赫夫曼编码表 转成String

    //使用一个方法，将前面的方法封装起来，便于调用
    private static byte[] huffmanZip(byte[] bytes) {

        List<Node> nodes = getNodes(bytes);
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;

    }
    //编写一个方法对压缩数据的解码
    /**
     * @param huffmanCodes 赫夫曼编码表
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 返回的是
     */
    private static byte[] deCode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //先得道HuffmanBytes 对应的二进制的字符串，形式：1010100010111
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节。因为最后一个自己不需要补高位
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        System.out.println("赫夫曼编码字节数组对应的二进制字符串" + stringBuilder);

        //把字符串按照指定的赫夫曼编码进行解码
        //吧把赫夫曼编码表进行调换，因为反向查询 a->100  100->a
        HashMap<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //创建一个集合，存放byte
        ArrayList<Byte> list = new ArrayList<>();

        //I可以理解成是索引，扫描stringBuilder
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;//相当于是一个干活的，一直往后跑
            boolean flag = true;
            Byte b = null;
            while (flag) {
                //扫描stringBuilder
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);//看看扫描到的这个二进制在反编码表中有没有
                if (b == null) {//如果没有这个编码，让count继续往后扫描
                    count++;
                } else {//如果找到一个直接退出
                    flag = false;
                }
            }
            //while循环退出的时候，说明找到一个
            list.add(b);
            i += count;//subString不包含尾
        }
        //for循环结束以后，list就存放了所有的字符
        //把list中的数据放入到byte[]并返回
        byte b[] = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            b[i] = list.get(i);
        }
        return b;
    }


    //编写一个方法，将字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]

    /**
     * 将一个byte转成二进制的字符串
     *
     * @param flag 标示是否需要补高位，如果是true，就标示要补高位，如果是false表示不补，如果是最后一个字节无需补高位
     * @param b    传入的byte
     * @return 是该b对应的二进制的字符串（按补码返回的）
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存b
        int temp = b;//将b转成int
        //如果是正数还存在补高位
        if (flag) {
            temp |= 256;//按位与256    1 0000 0000 | 0000 0000 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp);//返回的是temp对应的二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }


    }

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
        //System.out.println("赫夫曼编码压缩后的二进制字符串" + stringBuilder.toString());
        //确定编码后的长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
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
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
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
