package com.hjfstudy.tree.threadedbinarytree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //二叉树，后面递归创建，现在手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //测试：以10号节点做测试
        HeroNode leftNode = node5.getLeft();
        HeroNode right = node5.getRight();
        System.out.println("10号节点的前驱节点是" + leftNode);
        System.out.println("10号节点的后继节点是" + right);


        //线索化二叉树之后，不能用之前的遍历方式了
        System.out.println("使用线索化的方式遍历线索化为二叉树");
        threadedBinaryTree.threadedList();


    }
}

//创建线索二叉树
class ThreadedBinaryTree {
    private HeroNode root;
    HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //重载
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    //遍历线索化二叉树的方法
    public void threadedList() {
        //定义一个变量存储当前遍历的节点
        HeroNode node = root;

        while (node != null) {
            //循环判断找到leftType == 1的节点，第一个就是8
            //后面随着遍历而变化，因为leftType == 1时，说明该节点是按照锡奥所花
            //处理之后的节点
            while (node.getLeftType() == 0) {//这里就在一直找
                node = node.getLeft();
            }
            //找到过后输出
            System.out.println(node);
            //如果当前节点的右指针是后继节点，就一直输出
            while (node.getRightTyp() == 1){
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node = node.getRight();
        }

    }

    //编写对二叉树进行中序线索化的方法
    public void threadedNodes(HeroNode node) {
        //如果node==null，不能线索化
        if (node == null) {
            return;
        }
        //一、先线索化左子树
        threadedNodes(node.getLeft());
        //二、再处理当前节点的前驱节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);//指向的是前驱节点
        }

        //处理当前节点的后继节点（这一步是在进行下一个节点操作的时候才做的）
        if (pre != null && pre.getRight() == null) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改当前节点的右指针类型
            pre.setRightTyp(1);
        }
        //最关键的一步：每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;
        //三、线索化右子节点
        threadedNodes(node.getRight());
    }

    //删除节点
    public void delNode(int no) {
        if (root != null) {//先判断一下根节点是不是空的
            if (root.getNo() == no) {
                root = null;
            } else {
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

//创建节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //说明：
    //如果leftType == 0 表示指向的是左子树，如果==1表示指向前驱节点
    //如果rightType == 0 表示指向的是右子树，如果==1表示指向后继节点
    private int leftType;
    private int rightTyp;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightTyp() {
        return rightTyp;
    }

    public void setRightTyp(int rightTyp) {
        this.rightTyp = rightTyp;
    }

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
