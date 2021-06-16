package com.hjfstudy.search;

public class SeqSearch {
    public static void main(String[] args) {

        int[] arr={1,8,10,89,1000,1234};
        int i = seqSearch(arr, 10);
        System.out.println(i);
    }

    public static int seqSearch(int[] arr,int value){
        for (int i = 0;i < arr.length;i++){
            if (arr[i] == value){
                return i;
            }
        }
        return -1;
    }
}
