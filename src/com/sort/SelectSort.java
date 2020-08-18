package com.sort;

public class SelectSort {
    public static void main(String[] args) {
        BubbleSort ran = new BubbleSort();

        int[] a = ran.randombach();
        int b;

        //선택정렬
        for(int i = 0; i< a.length -1; i++){
            for(int j = i+1; j < a.length; j++){
                if(a[i] > a[j]){
                    b = a[j];
                    a[j] = a[i];
                    a[i] = b;
                }
            }
        }
        System.out.println("\n--------------------------------------");
        for(int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }

    }
}
