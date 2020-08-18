package com.sort;

import java.util.Arrays;

public class QuickSort {
    static int partition(int[] array, int start, int end){
        int pivot = array[(start+end)/2];
        while (start <= end){
            while (array[start] < pivot){
                start++;
            }
            while (array[end] > pivot){
                end--;
            }
            if(start <= end){
                int tmp = array[start];
                array[start] = array[end];
                array[end] = tmp;
                start++;
                end--;
            }
        }
        return start;
    }
    static int[] quickSort(int[] array, int start, int end){
        int p = partition(array, start, end);
        if(start < p-1){
            quickSort(array, start, p-1);
        }
        if(p < end){
            quickSort(array, p, end);
        }
        return array;
    }

    public static void main(String[] args) {
        BubbleSort ran = new BubbleSort();
        int[] array = ran.randombach();

        array = quickSort(array, 0, array.length-1);
        System.out.println();
        System.out.println("퀵 정렬 후");
        System.out.println(Arrays.toString(array));
    }
}
