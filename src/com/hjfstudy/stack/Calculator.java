package com.hjfstudy.stack;

public class Calculator {
    public static void main(String[] args) {
        //完成表达式的运算
        String expression = "90+2*6-2";
        //创建两个栈，一个数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //初始化遍历
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int res = 0;
        int oper = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch
        String keepNum = "";
        //开始while循环的扫描expression
        while (true) {
            //依次得到每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么数还是符号
            if (operStack.isOper(ch)) {//如果是符号的话，分两种情况入栈
                if (!operStack.isEmpty()) {
                    if (operStack.priority(ch) <= operStack.priority(operStack.peak())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = operStack.cal(num1, num2, oper);
                        //将得到的结果，入数栈，然后将当前的操作符入符号栈。
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                } else {//如果当前符号栈是空的，直接入栈
                    operStack.push(ch);

                }
            } else {//是数字，直接入栈
//                numStack.push(ch - 48);
                //分析思路:
                //1.当处理多位数的时候，不能发现一个数就立即入栈，因为他可能是多位数
                //2、在处理数，需要向expression表达式的index后再看一位，如果是数就进行扫描，如果是符号才入栈
                //3、因此我们需要定义一个变量字符串，用于拼接
                keepNum += ch;
                //判断是不是到最后一位了
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        //如果最后一位是运算符，则入栈keepNum = "1" 或者"123"
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }


            }
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        //表达式扫描完毕，顺序的从数栈和符号栈中pop出相应的数字和符号，并运行
        while (true) {
            if (operStack.isEmpty()) {
                //如果符号栈为空，说明计算结束了
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = operStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        //打印结果
        System.out.printf("表达式 %s = %d", expression, numStack.pop());


    }
}

class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//数组，模拟栈
    private int top = -1;//表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //返回当前栈顶值，但不是真正的pop
    public int peak() {
        return stack[top];
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //判断栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈已满，无法入栈！");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，无法出栈！");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况（遍历栈）
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据！");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级，优先级是程序员定的，优先级使用数字表示
    //数字越大，则优先级就越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是不是运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        //假定结果返回的是res
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}
