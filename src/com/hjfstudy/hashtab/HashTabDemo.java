package com.hjfstudy.hashtab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        //创建一个HashTab
        HashTab hashTab = new HashTab(7);

        //创建一个接收器
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("请输入id");
                    int id = scanner.nextInt();
                    System.out.println("请输入姓名");
                    String name = scanner.next();
                    //创建一个雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id =  scanner.nextInt();
                    hashTab.findById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }

        }

    }

}

//编写HashTab 管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    public HashTab(int size) {
        this.empLinkedListArray = new EmpLinkedList[size];
        //这里不要忘了初始化每一条链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
        this.size = size;
    }

    //遍历HashTab
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //添加元素的方法
    public void add(Emp emp) {
        int empLinkedListNo = hashFun(emp.id);
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //散列函数
    public int hashFun(int id) {
        return id % size;
    }

    //根据输入的id查找雇员
    public void findById(int id) {
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if (emp != null) {//找到了
            System.out.printf("在第%d条链表中找到雇员 id = %d/n",empLinkedListNo + 1,id);
        }else {
            System.out.println("在哈希表中，没有找到该雇员");
        }
    }

}

//创建一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建一个EmpLinkedList
class EmpLinkedList {
    //头指针
    private Emp head;//默认为null

    //添加雇员方法
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个雇员，遍历一下，找到最后一个节点，放到最后
        Emp temp = head;
        while (true) {
            if (temp.next == null) {//temp就是最后一个
                break;
            }
            temp = temp.next;
        }//退出的时候添加进去
        temp.next = emp;
    }

    //遍历链表
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + (no + 1) + "条链表:");
            return;
        }
        Emp temp = head;
        System.out.print("第" + (no + 1) + "条链表信息为:");
        while (true) {
            System.out.printf("=> id = %d name = %s\t", temp.id, temp.name);
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        System.out.println();
    }

    //查找雇员的方法
    public Emp findEmpById(int id) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("链表为空！");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {//找到了
                break;//这时curTemp就指向要查找的雇员
            }
            //退出
            if (curEmp.next == null) {//说明遍历完毕都没有找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}
