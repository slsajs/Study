package com.sort;

public class InsertionSort {
    public static void main(String[] args) {
        BubbleSort ran = new BubbleSort();
        int[] a = ran.randombach();
        int b;
        int j;

        //삽입정렬
        for(int i = 0; i < a.length; i++){
            b = a[i];
            for(j = i-1; j >= 0 && a[j] > b; j--){
                a[j+1] = a[j];
            }
            a[j+1] = b;
        }
        System.out.println("\n--------------------------------------");
        for (int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }
    }
}
