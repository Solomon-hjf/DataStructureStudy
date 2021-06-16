package com.hjfstudy.linkedlist;

public class Josephu {
    public static void main(String[] args) {
        //测试
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(25);
        circleSingleLinkedList.list();


        //测试小孩出圈顺序
        System.out.println("约瑟夫环问题测试");
        circleSingleLinkedList.countBoy(10,9,25);

    }
}

//构建一个环形链表
class CircleSingleLinkedList {
    //首先要有第一个节点
    Boy first = new Boy(-1);
    Boy curBoy = null;

    //添加小孩节点，构成一个环形的链表
    public void addBoy(int nums) {
        //先进行一个数据校验
        if (nums < 1) {//数据量不够，退出
            System.out.println("nums的值不正确");
            return;
        }
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                //如果不是第一个小孩
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前的环形链表
    public void list() {
        if (first == null) {
            System.out.println("链表为空！");
            return;
        }
        //first是第一个小孩，不能动
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号为 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {//说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    //根据用户的输入，计算小孩出圈顺序

    /**
     * @param startNo  表示从第几个小孩开始数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //数据校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("数据输入有误，请重新输入！");
            return;//直接退出不玩了
        }
        //先创建一个辅助指针,放到first后面
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {//说明helper指向最后小孩节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让helper和first移动到要报数的地方,移动startNo -1 下
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //退出循环的时候，说明已经到达地方了
        //开始报数，移动countNum - 1 次，出圈
        while (true) {
            if (helper == first) {//说明已经到最后，剩下一个小孩了
                break;
            }
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //退出遍历，说明找到地方了，现在让小孩出圈
            System.out.printf("第%d个小孩出圈\n", first.getNo());
            //出圈
            first = first.getNext();
            helper.setNext(first);
        }
        //退出循环的时候，说明圈子里还剩下最后一个小孩
        System.out.printf("最后一个小孩%d还在圈中", helper.getNo());
    }

}

//构建小孩类
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
