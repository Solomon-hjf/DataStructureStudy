package com.hjfstudy.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //完成一个中缀表达式转后缀表达式
        //说明：1、1+((2+3)x4)-5 =  ------> 1 2 3 + 4 x +5 -
        //2、因为直接操作str不方便，因此现将1+((2+3)x4)-5 转换成ArrayList
        //3、将得到的中缀表达式的List 转换成 后缀表达式对应的List
        //即ArrayList [1,+,(,(,2,+3,)*4,)-,5]   转换成 ArrayList[1,2,3,+,4,*,+,5,-]

        //先定义一个逆波兰表达式
        //(3+4)*5-6
//        String suffixExpression = "3 4 + 5 * 6 -";
        //(30+4)*5-6 = 164
//        String suffixExpression = "30 4 + 5 * 6 -";
//        //测试一把转换
//        List<String> res = getListString(suffixExpression);
//        System.out.println(res);
//
//        int val = calculator(res);
//        System.out.println("计算的结果是：" + val);

        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的List=" + infixExpressionList);

        List<String> suffixExpression = parseSuffixExpression(infixExpressionList);
        System.out.println("后缀表达式对应的List" + suffixExpression);

        int value = calculator(suffixExpression);
        System.out.printf(suffixExpression + "的计算结果为" + value);

    }

    public static List<String> parseSuffixExpression(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<String>();//符号栈
        ArrayList<String> s2 = new ArrayList<String>();//数栈
        //遍历传进来的List
        for (String item : ls) {
            if (item.matches("\\d+")) {//如果item是一个数加入s2
                s2.add(item);
            } else if (item.equals("(")) {//如果遇到的是左括号，直接压入s1
                s1.push(item);
            } else if (item.equals(")")) {//如果遇到的是右括号
                //则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {//如果遇到的是运算符，比较其于s1栈顶运算符的优先级
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item压入栈中
                s1.push(item);

            }

        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    public static List<String> toInfixExpressionList(String s) {
        //定义一个List存放中缀表达式
        ArrayList<String> ls = new ArrayList<>();
        int i = 0;//指针
        String str;//用于拼接字符串
        char c;//每遍历到一个字符就放入到c
        do {
            //如果c是一个非数字，就需要加入到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;//指针后移
            } else {//c是一个数字，需要拼接
                str = "";//现将str置空
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }


    //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //先用正则表达式分割字符串
        String[] split = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        //把字符串放到一个ArrayList中
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成对波兰表达式计算

    /**
     * 1. 从左至右扫描，将对应3和4压入堆栈
     * 2. 遇到+运算符，因此弹出4和3（4位栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
     * 3. 将5入栈
     * 4. 接下来是x运算符，因此弹出5和7，计算出7 x 5 = 35，将35入栈
     * 5. 将6入栈
     * 6. 最后是-运算符，计算出35-6的值，即29，由此得出最终结果
     */
    public static int calculator(List<String> list) {
        //创建一个栈
        Stack<String> stack = new Stack<>();
        //遍历list
        for (String item : list) {
            //这里使用正则表达式取数
            if (item.matches("\\d+")) {//匹配的多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数,并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                //判断这里的运算符是什么
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("您的输入有误，请重新输入！");
                }
                //将运算的结果压入栈中
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类Operation 可以返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
//                System.out.println("不存在这样操作符！");
                break;
        }
        return result;
    }
}
