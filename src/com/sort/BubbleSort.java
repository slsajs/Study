package com.sort;

import java.util.Arrays;

public class BubbleSort {

    public static int[] randombach(){
        int[] a = new int[10];
        int b;
        //난수 생성
        for(int i = 0; i<a.length; i++){
            a[i] = (int)(Math.random() * 100);
        }
        System.out.print(Arrays.toString(a));
        return a;
    }

    public static void main(String[] args) {

        int[] a = randombach();
        int b;

        //버블 정렬
        for(int j = 0; j<a.length; j++){
            for(int k = 0; k<a.length -j -1; k++){
                if(a[k] > a[k+1]){
                    b = a[k];
                    a[k] = a[k+1];
                    a[k+1] = b;
                }
            }
        }
        System.out.println("\n--------------------------------------");
        for(int i = 0; i<a.length; i++){
            System.out.println(a[i]);
        }

    }
}
