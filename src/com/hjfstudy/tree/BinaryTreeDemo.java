package com.hjfstudy.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

//        //测试前序遍历
//        System.out.println("前序遍历");
//        binaryTree.preOrder();  //1-2-3-5-4
//        //测试中序遍历
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();//2-1-5-3-4
//        //测试中序遍历
//        System.out.println("后序遍历");
//        binaryTree.postOrder();//2-5-4-3-1

        //前序遍历查找
//        System.out.println("前序遍历查找方式~~~~~");
//        HeroNode heroNode = binaryTree.preOrderSearch(5);
//        if (heroNode != null) {
//            System.out.printf("找到了，信息为 no = %d,name = %s", heroNode.getNo(), heroNode.getName());
//...         } else {
//            System.out.printf("没有找到 no = %d的英雄", 5);
//        }

        //中序遍历查找
//        System.out.println("前序遍历查找方式~~~~~");
//        HeroNode heroNode = binaryTree.infixOrderSearch(5);
//        if (heroNode != null) {
//            System.out.printf("找到了，信息为 no = %d,name = %s", heroNode.getNo(), heroNode.getName());
//        } else {
//            System.out.printf("没有找到 no = %d的英雄", 5);
//        }
        //hou序遍历查找
//        System.out.println("hou序遍历查找方式~~~~~");
//        HeroNode heroNode = binaryTree.postOrderSearch(5);
//        if (heroNode != null) {
//            System.out.printf("找到了，信息为 no = %d,name = %s", heroNode.getNo(), heroNode.getName());
//        } else {
//            System.out.printf("没有找到 no = %d的英雄", 5);
//        }

        System.out.println("删除前：");
        binaryTree.preOrder();  //1，2，3，5，4
        binaryTree.delNode(3);
        System.out.println("删除后");
        binaryTree.preOrder();//1，2，3，4
    }
}

//定义一个二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //删除节点
    public void delNode(int no) {
        if (root != null) {//先判断一下根节点是不是空的
            if (root.getNo() == no){
                root = null;
            }else {
                root.delNode(no);
            }
        } else {//如果跟节点是空的
            System.out.println("根节点为空！");
        }
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历！");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历！");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else System.out.println("二叉树为空，");
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}

//先创建HeroNode节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //递归删除节点
    //1、如果要删除的节点是子叶节点，就删除该节点
    //2、如果删除的节点不是叶子节点，就删除该节点的子树
    public void delNode(int no) {

        //判断当前节点的左子节点是不是要删除的节点
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        //判断当前节点的左子节点是不是要删除的节点
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //如果当前节点的左右子节点都不是要删除的节点
        //继续向左子树递归
        if (this.left != null) {
            this.left.delNode(no);
        }
        //左边递归完了，没有找到就向右边递归
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    //编写前序遍历的方法
    public void preOrder() {
        System.out.println(this);//先输出父节点
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //编写中序遍历的方法
    public void infixOrder() {
        //递归想左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //编写后序遍历的方法
    public void postOrder() {
        //先向左子树递归后序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        //向右子树递归后序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        //输出当前节点
        System.out.println(this);

    }

    //前序遍历查找的方法
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历！");
        //先判断当前节点的no是否等于要查找的no
        if (this.no == no) {
            return this;
        }
        //定义一个接收节点
        HeroNode resNode = null;
        //如果当前节点不是，那就想左子节点找
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {//左子节点递归查找完了，如果找到，就返回结果
            return resNode;
        }
        //如果左子节点递归查找完了，没有找到，就右递归找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;//不管找没找到，都返回（没找到返回null）
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入中序遍历！");
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入后序遍历！");
        if (this.no == no) {
            return this;
        }
        return resNode;
    }
}
