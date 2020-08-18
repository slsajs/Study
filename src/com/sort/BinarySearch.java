package com.sort;

import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
       int[] a = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,22,34,56,88};

        Scanner sc = new Scanner(System.in);
        int key = sc.nextInt();

        binarySearch(a,key);
    }
    private static void binarySearch(int[] a, int key){

        int left = 0;

        int right = a.length-1;
        int mid;

        while (left <= right){

            mid = (left + right)/2;

            if(key == a[mid]){
                System.out.println(key + "값의 Array Index 위치 : " + mid);
                break;
            }

            if(key < a[mid]){
                right = mid-1;
            }else {
                left = mid+1;
            }
        }

    }
}
