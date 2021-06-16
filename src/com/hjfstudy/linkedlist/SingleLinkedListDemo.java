package com.hjfstudy.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");

        HeroNode hero5 = new HeroNode(5,"宋江","及时雨");
        HeroNode hero6 = new HeroNode(6,"卢俊义","玉麒麟");
        HeroNode hero7 = new HeroNode(7,"吴用","智多星");
        HeroNode hero8 = new HeroNode(8,"林冲","豹子头");
        //创建单向链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        //加入
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        //第二种加入
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero7);
        singleLinkedList.addByOrder(hero8);

        singleLinkedList2.addByOrder(hero2);
        singleLinkedList2.addByOrder(hero4);
        singleLinkedList2.addByOrder(hero5);
        singleLinkedList2.addByOrder(hero6);


        //显示一下
        singleLinkedList.list();

//        System.out.println("----------------修改过后的结果----------------");
//        HeroNode newHero = new HeroNode(9, "小卢", "玉麒麟~~");
//        singleLinkedList.update(newHero);
//        singleLinkedList.list();
//
//        singleLinkedList.delete(1);
//        singleLinkedList.delete(4);
//        singleLinkedList.delete(2);
//        singleLinkedList.delete(3);
//        System.out.println("----------------删除过后的结果----------------");
//        singleLinkedList.list();
//        System.out.println(getLength(singleLinkedList.getHead()));
//
//        System.out.println("----------------查找倒数第k个节点----------------");
//        System.out.println(findLastIndexHero(singleLinkedList.getHead(),4));

//        //测试单链表的反转
//        reverseLinkedList(singleLinkedList.getHead());
//        System.out.println("----------------反转后的单链表----------------");
//        singleLinkedList.list();

//        System.out.println("----------------逆序打印单链表----------------");
//        reversePrint(singleLinkedList.getHead());


        System.out.println("****************单链表1****************");
        singleLinkedList.list();
        System.out.println("****************单链表2****************");
        singleLinkedList2.list();
        System.out.println("----------------合并两个单链表----------------");
        HeroNode temp = mergeLists(singleLinkedList.getHead().next, singleLinkedList2.getHead().next);
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }

    }

    //方法：获取到单链表节点的个数(如果是带头节点的链表，需求不统计头节点)
    public static int getLength(HeroNode head){
        if (head.next == null){
            //这是一个空节点
            return 0;
        }
        int length = 0;
        HeroNode temp = head.next;
        while (temp != null){
            length++;
            temp = temp.next;
        }
        return length;
    }

    //查找单链表中倒数第k个节点
    //思路：1、先编写一个方法，接收head节点，同时接收一个index
    //2、index表示倒数第index个节点
    //3、先把链表从头到尾开始遍历，看一共有几个节点，我们从链表的第一个开始遍历（size - index）个，就可以得到

    public static HeroNode findLastIndexHero(HeroNode head,int index){
        if (head.next == null){
            System.out.println("链表为空，无法查找");
        }
        HeroNode temp = head.next;
        //先找到一共几个节点
        int k = 1;
        while (temp != null){
            temp = temp.next;
            k++;
        }
        //先做一个数据的校验
        if (index <= 0 ||index > k){
            return null;
        }
        //找出正数的第（k-index）节点就是要找的
        int m = 1;
        HeroNode temp1 = head.next;
        while (m != (k-index)){
            temp1 = temp1.next;
            m++;
        }
        return temp1;
    }

    //单链表的反转（腾讯）
    public static void reverseLinkedList(HeroNode head){
        if(head.next == null|| head.next.next == null){
            System.out.println("无需反转");
            return;
        }
        //定义一个辅助的指针，帮助遍历原来的链表
        HeroNode curr = head.next;
        HeroNode next;//指向当前节点的下一个节点
        //先创建一个新的链表
        HeroNode reverseHead = new HeroNode(0,"","");
        //遍历传进来的链表，每取出来一个就把它放到新链表的最前面
        while (curr != null){
            next = curr.next;//先暂时保留当前节点的下一个节点
            curr.next = reverseHead.next;
            reverseHead.next = curr;    //将curr加到新的链表上
            curr = next;
        }
        //将head.next指向reverseHead.next
        head.next = reverseHead.next;
    }

    //使用stack逆序打印链表
    public static void reversePrint(HeroNode head){
        if (head.next == null){
            return;
        }
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //将链表的所有节点压如栈中
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

    //合并两个有序链表，合并之后的单链表仍然是有序的
    public static HeroNode mergeLists(HeroNode list1,HeroNode list2){
        if (list1 == null){
            return list2;
        }else if(list2 == null){
            return list1;
        }

        HeroNode newHead = null;
        if (list1.no < list2.no){
            newHead = list1;
            newHead.next = mergeLists(list1.next,list2);
        }else {
            newHead = list2;
            newHead.next = mergeLists(list2.next,list1);
        }
        return newHead;
    }

}

//创建一个LinkedList链表
class SingleLinkedList{
    //先初始化一个头节点，头节点不要动,不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {//返回头节点
        return head;
    }

    //添加的方法,添加节点到单向链表中
    //思路：当不考虑编号的顺序时
    //找到当前链表的最后一个节点
    //将最后一个节点的next指向新的节点
    public void add(HeroNode heroNode){
        //因为head节点不能动，因此需要一个辅助变量temp
        HeroNode temp = head;
        //遍历链表，找到最后一个节点
        while (true){
            //最后一个节点
            if (temp.next == null){
                break;
            }
            //如果没有找到最后,temp往后移
            temp = temp.next;
        }
        //当退出循环的时候，temp就指向链表的最后了
        temp.next = heroNode;
    }

    //第二种添加方式，根据排名插入到指定的位置
    public void addByOrder(HeroNode heroNode){
        //因为头节点不能动，因此通过一个辅助指针来帮助找到位置
        //因为单链表，我们找到的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;//flag表示添加的编号是否存在，默认为false
        while (true){
            if (temp.next == null){//最后一个节点了
                break;
            }
            if (temp.next.no > heroNode.no){//位置找到
                break;
            }else if (temp.next.no == heroNode.no){//说明希望添加的编号已经存在了
            flag = true;//说明编号存在了
            break;
            }
            temp = temp.next;//后移，遍历当前链表
        }
        //判断flag
        if (flag){
            System.out.printf("当添加的编号%d以及存在，添加失败\n",heroNode.no);
        }else {//插入到链表当中，temp后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    //修改节点的信息，根据no编号来修改，即no不能变
    public void update(HeroNode newHeroNode){
        //判断是否为空
        if(head.next == null){
            System.out.println("链表为空！");
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true){
            if (temp == null){
                //已经遍历完链表了
                break;
            }
            if (temp.no == newHeroNode.no){//找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断找到没有
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else {
            System.out.printf("没有找到编号%d的节点信息\n",newHeroNode.no);
        }
    }

    //删除节点
    //head节点不能动
    public void delete(int no){
        HeroNode temp = head;
        //判断链式是不是空的


        boolean flag = false;
        while (true){
            if (temp.next == null){
                System.out.println("链表到最后了，不能删除!");
                break;
            }
            if(temp.next.no == no){//找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除的%d节点不存在",no);
        }
    }

    //显示链表，遍历
    public void list(){
        if (head.next == null){//判断链表是否为空
            System.out.println("链表为空！");
        }
        //头节点不能动,因此需要一个辅助节点来遍历
        HeroNode temp = head.next;
        while (true){
            if (temp == null){
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            temp = temp.next;
        }

    }

}
class HeroNode{
    //基本属性
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;
    //构造器
    public HeroNode(int hNo, String hName, String hNickName){
        this.no = hNo;
        this.name = hName;
        this.nickName = hNickName;
    }
    //重写toString方法
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}