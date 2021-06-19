package com.hjfstudy.avl;

public class AVLTreeDemo {
    public static void main(String[] args) {

//        int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = {10,12,8,9,7,6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        //创建一个AVTTree
        AVLTree avlTree = new AVLTree();
        //添加结点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历");
        avlTree.infixOrder();

//        System.out.println("在没有做平衡处理之前：");
        System.out.println("树的高度：" + avlTree.getRoot().height());
        System.out.println("树的左子树高度：" + avlTree.getRoot().leftHeight());
        System.out.println("树的右子树高度：" + avlTree.getRoot().rightHeight());
        System.out.println("当前树的根结点" + avlTree.getRoot());
    }
}

//创建AVLTree
class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    //查找要删除的结点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父结点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //编写方法

    /**
     * @param node 传入的结点（当做二叉排序树的根节点）
     * @return 返回以node为根节点的二叉排序树的最小根节点的值，还要删除这个值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环查找左节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //这是target就指向了最小的结点
        //删除这个结点
        delNode(target.value);
        return target.value;
    }

    //删除结点的方法
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //1、需要先找到要删除的结点
            Node targetNode = search(value);
            //如果没有找到要删除的结点
            if (targetNode == null) {
                return;
            }
            //如果我们发现当前这颗二叉排序树只有一个结点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //找到targetNode的父节点
            Node parent = searchParent(value);
            //如果要删除的结点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是父节点的左子结点还是右子结点
                if (parent.left != null && parent.left.value == value) {//是左子节点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {//右子结点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {//删除有两颗子树的结果
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;

            } else {//删除只有一颗子树的结点
                //如果要删除的结点有左子节点
                if (targetNode.left != null) {
                    if (parent != null) {
                        //如果targetNode是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else { //如果targetNode是parent的右子节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {//如果要删除的结点有右子结点
                    if (parent != null) {
                        //如果targetNode是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else { //如果targetNode是parent的右子节点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    //添加节点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空，不能遍历！");
        }
    }
}

//创建Node节点
class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }


    //左旋转的方法
    private void leftRotate() {
        //创建新的结点，以当前根节点的值
        Node newNode = new Node(this.value);
        //把新结点的左子树设置为当前结点的左子树
        newNode.left = this.left;
        //把新结点的右子树设置成当前结点的右子树的左子树
        newNode.right = this.right.left;
        //把当前结点的值换成右子节点的值
        this.value = this.right.value;
        //把当前结点的右子树设置成当前结点的右子树的右子树
        this.right = right.right;
        //把当前结点的左子树（左子节点）设置成新的结点
        this.left = newNode;
    }

    //右旋转
    private void rightRotate() {
        Node newNode = new Node(this.value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.value = this.left.value;
        this.left = left.left;
        this.right = newNode;
    }


    //返回当前结点的高度，以该结点为根节点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //查找要删除的结点
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {//如果要查找的值小于当前结点值向做左子树递归查找
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {//如果查找的值大于当前结点的值，向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }
    //查找要删除节点的父节点

    /**
     * @param value 要找到结点的值
     * @return 返回的是研删除结点的父节点，没有接返回null
     */
    public Node searchParent(int value) {
        //如果当前结点就是要删除结点的父类
        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前结点的值，并且结点的左子节点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);//向左子结点递归
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);//向右子结点递归
            } else {
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //添加节点的方法
    public void add(Node node) {
        if (node == null) {//如果节点是空的，就直接返回
            return;
        }
        if (node.value < this.value) {//如果加进来的节点的值小于当前节点的值
            //判断当前节点的左子节点
            if (this.left == null) {
                //如果是空的，就直接挂上去
                this.left = node;
            } else {//如果当前节点不为空
                //向左递归
                this.left.add(node);
            }
        } else {//如果传进来的值大于当前节点的值
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
        //当添加完有一个结点后，如果：（右子树的高度 - 左子树的高度） > 1 ,左旋转
        if (rightHeight() - leftHeight() > 1) {
            //如果它的右子树的左子树的高度大于它的右子树的高度
            if (this.right != null && this.right.leftHeight() > this.leftHeight()){
                //先对右子节点进行右旋转
                this.right.rightRotate();
                //然后再对当前结点进行左旋转
                leftRotate();
            }else {
                //直接左旋转
                leftRotate();
            }
            return;//必须要进行返回
        }
        //（左子树的高度 - 右子树的高度） > 1
        if (leftHeight() - rightHeight() > 1) {
            //如果它的左子树的的右子树高度大于它的右子树的高度
            if (this.left != null && this.left.rightHeight() > this.rightHeight()) {
                //先对当前结点的左结点进行右旋转
                this.left.leftRotate();
                //再对根结点进行右旋转
                this.rightRotate();
            } else {
                rightRotate();//右旋转
            }

        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

}
