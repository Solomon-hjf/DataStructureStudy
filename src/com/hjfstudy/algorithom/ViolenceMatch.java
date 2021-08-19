package com.hjfstudy.algorithom;

public class ViolenceMatch {
    public static void main(String[] args) {
        String s1 = "尚硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String s2 = "尚硅谷你尚硅谷你";
        int index = violenceMatch(s1, s2);
        System.out.println("index = " + index);
    }

    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int str1Length = s1.length;
        int str2Length = s2.length;

        int i = 0;
        int j = 0;
        //暴力匹配
        while (i < str1Length && j < str2Length) {//没有越界的情况下
            if (s1[i] == s2[j]) {//如果匹配
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == str2Length) {
            return i - j;
        } else {
            return -1;
        }
    }
}
