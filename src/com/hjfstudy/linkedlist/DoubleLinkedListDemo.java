package com.hjfstudy.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        //添加
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        //创建双向链表对象
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);

        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);



        doubleLinkedList.list();

//        //测试修改
//        System.out.println("************测试修改后的链表************");
//        HeroNode2 hero5 = new HeroNode2(4,"林冲~~~","小豹子头");
//        doubleLinkedList.update(hero5);
//        doubleLinkedList.list();
//
//        //测试删除
//        doubleLinkedList.delete(4);
//        doubleLinkedList.delete(3);
//        doubleLinkedList.delete(2);
//        doubleLinkedList.delete(1);
//        System.out.println("************测试删除后的链表************");
//        doubleLinkedList.list();
    }


}

//创建一个双向链表类
class DoubleLinkedList {
    //先初始化一个头节点，头节点不动，不放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    //返回头节点

    public HeroNode2 getHead() {
        return head;
    }

    //按照顺序添加
    public void addByOrder(HeroNode2 hero) {
        HeroNode2 temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                //最后一个节点了
                break;
            }
            if (temp.next.no > hero.no) {
                break;
            } else if (temp.next.no == hero.no) {
                System.out.println("节点已经存在");
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (!flag) {//要注意先后顺序
            hero.next = temp.next;
            temp.next = hero;
            hero.pre = temp;
        } else {
            System.out.println("您的输入有误！");
        }
    }

    //遍历双向链表的方法
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空！");
        }
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //添加（默认添加到末尾）
    public void add(HeroNode2 hero) {
        //头节点不动
        HeroNode2 temp = head;
        //遍历链表
        while (true) {
            if (temp.next == null) {
                //链表到末尾了
                break;
            }
            temp = temp.next;
        }
        //退出循环的时候，表明已经到了链表的末尾
        temp.next = hero;
        hero.pre = temp;
    }

    //修改节点信息
    public void update(HeroNode2 heroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        int no = heroNode.no;
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                //表示已经遍历完链表了
                break;
            }
            if (temp.no == no) {
                //说明找到了
                flag = true;
                break;

            }
            temp = temp.next;
        }
        //遍历完了
        if (flag) {
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        } else {
            System.out.printf("您要添加的节点%d不存在", heroNode.no);
        }
    }

    //从双向链表中删除一个节点
    public void delete(int delNo) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;   //标识是否找到
        while (true) {
            if (temp == null) {
                //链表到最后了
                System.out.println("没有找到要删除的节点。");
                break;
            }
            if (temp.no == delNo) {
                //找到对应节点了
                flag = true;
                break;

            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            //这里要考虑，如果是最后一个节点呢？就不需要执行下面这句话
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("您要删除的节点%d不存在", delNo);
        }

    }
}

//定义一个HeroNode2节点类
class HeroNode2 {
    //基本属性
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;
    public HeroNode2 pre;

    //构造器
    public HeroNode2(int hNo, String hName, String hNickName) {
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