package com.hjfstudy.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {

        int[] arr = {7, 3, 10, 12, 5, 1, 9, 0};
        BinaryTree binaryTree = new BinaryTree();
        //循环的添加节点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binaryTree.add(new Node(arr[i]));
        }

        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树");
        binaryTree.infixOrder();

        //测试删除叶子结点
//        binaryTree.delNode(2);
//        binaryTree.delNode(5);
//        binaryTree.delNode(9);
        binaryTree.delNode(10);
        System.out.println("删除节点！");
        binaryTree.infixOrder();


    }
}

class BinaryTree {
    private Node root;

    //查找要删除的结点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父节点
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
     * @return 返回的 以node为根节点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //虚幻的查找左子节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //此时target指向了最小值
        //删除最小结点
        delNode(target.value);
        return target.value;
    }


    //删除结点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //找到要删除的结点
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            //如果我们发现这课二叉排序树只有一个结点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //找到targetNode的父节点
            Node parent = searchParent(value);
            //如果要删除的结点是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode 是父节点的左子节点还是右子节点
                if (parent.left != null && parent.left.value == targetNode.value) {
                    //是左子节点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == targetNode.value) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                //删除的是有两颗子树的结点
                int minValue = delRightTreeMin(targetNode.right);
                targetNode.value = minValue;

            } else {//删除只有一颗子树的结点
                //如果要删除的结点有左子节点
                if (targetNode.left != null) {
                    if (parent != null) {
                        if (parent.left.value == value) {
                            //如果targetNode是parent的左子节点
                            parent.left = targetNode.left;
                        } else {//如果要删除的结点有右子节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    if(parent !=null){
                        //如果要删除的结点有右子节点
                        if (parent.left.value == value) {
                            //如果targetNode是parent的左子节点
                            parent.left = targetNode.right;
                        } else {//如果targetNode是parent的右子节点
                            parent.right = targetNode.right;
                        }
                    }else {
                        root = targetNode.right;
                    }
                }

            }

        }
    }

    //添加节点
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
            System.out.println("当前二叉树为空");
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }


    //查找要删除的结点
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {//如果要找的值小于当前结点的值，向左子树递归查找
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {//如果要找的值不小于当前结点的值，向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除节点的父节点
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);//向左子树递归查找
            } else if (value >= this.value && this.right != null) {//向右子树递归查找
                return this.right.searchParent(value);
            } else {
                return null; //没有找到父节点
            }
        }
    }


    //添加节点的方法
//递归形式添加节点，注意需要满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //判断传入节点的值和当前节点的关系
        if (node.value < this.value) {
            if (this.left == null) { //如果当前节点左子节点是空的直接挂上去
                this.left = node;
            } else {
                //递归向左子树添加
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                //递归向右子树添加
                this.right.add(node);
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

