package com.hjfstudy.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {

        int[] arr = {2, 7, 3, 10, 12, 5, 1, 9};
        //循环添加节点到二叉树
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        //中序遍历二叉树
        binarySortTree.infixOrder();
        binarySortTree.delNode(10);
        System.out.println("删除结点后");
        binarySortTree.infixOrder();
    }

}

//创建二叉排序树
class BinarySortTree {
    private Node root;

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