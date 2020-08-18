package com.sort;

import java.util.Arrays;

public class HeapSort {
    private static int[] data;
    private static int number = 10;

    public static void heap(int[] data, int number){
        for(int i = 1; i < number; i++){
            int child = i;
            while (child > 0){
                int parent = (child-1)/2;
                if(data[child] > data[parent]){
                    int temp = data[parent];
                    data[parent] = data[child];
                    data[child] = temp;
                }
                child = parent;
            }
        }
    }

    public static void main(String[] args) {
        BubbleSort ran = new BubbleSort();
        System.out.print("정렬 전 : ");
        int[] a = ran.randombach();
        data = new int[number];
        data = a;

        heap(data, number);

        for(int i = number -1; i > 0; i--){
            int temp = data[0];
            data[0] = data[i];
            data[i] = temp;

            heap(data, i);
        }

        System.out.print("\n정렬 후 : ");
        System.out.println(Arrays.toString(data));

    }

}
