package com.hjfstudy.algorithom;

import java.util.Arrays;

public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] next = kmpNext("ABCDABD");
        System.out.println("next=" + Arrays.toString(next));


        int index = kmpSearch(str1, str2, next);
        System.out.println("index = " + index);
    }

    //KMP搜索算法

    /**
     * @param str1 源字符串
     * @param str2 模式串
     * @param next 部分匹配表，是对应模式串的部分匹配表
     * @return 如果是-1就没匹配到
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        //遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {
            //需要考虑str1.charAt(i) != str2.charAt(j)
            //kmp的核心点
            while (j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }

            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取到一个字符串（子串）的部分匹配值表
    public static int[] kmpNext(String dest) {
        //首先创建一个next数组 保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串长度是1 部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //这里的i是后缀的末尾，j是前缀的末尾也代表包括i之前子串的最长相等前后缀的长度
            //当dest.charAt(i) != dest.charAt(j)时,需要从next[j-1]获取新的j
            //直到我们发现有 dest.charAt(i) == dest.charAt(j)时成立才退出
            //这是kmp算法的一个核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            //满足这个条件dest.charAt(i) == dest.charAt(j)时，部分匹配值就+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
