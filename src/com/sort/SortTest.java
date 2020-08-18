package com.sort;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class SortTest extends Thread {
    public static void main(String[] args) {
        System.out.print("배열 크기 지정 : ");
        Scanner sc = new Scanner(System.in);
        int snum = sc.nextInt();

        int [] num = new int[snum];

        System.out.println("난수 생성");
        for(int i = 0; i < snum; i++){
            num[i] = (int)(Math.random() * snum);
        }
        System.out.println("난수 생성완료");
        System.out.println();

        Bubble bubble = new Bubble(num);
        Selection selection = new Selection(num);
        Insertion insertion = new Insertion(num);

        bubble.start(); // 버블정렬 시간복잡도 O(n2)
        selection.start(); // 선택정렬 시간복잡도 O(n2)
        insertion.start(); // 삽입정렬 시간복잡도 O(n2) - 최악의 경우

    }
}

class Bubble extends Thread{
    int[] a;
    int b;

    Bubble(int[] array){
        a = array;
    }

    public void run(){
        System.out.println("버블정렬 시작");
        long start = System.currentTimeMillis();
        for(int i = 0; i < a.length; i++){
            for(int j = 0 ; j < a.length -i -1; j++){
                if(a[j] > a[j + 1]){
                    b = a[j];
                    a[j] = a[j + 1];
                    a[j+1] = b;
                }
            }
        }
        long endTime = System.currentTimeMillis();

        System.out.println("버블정렬 끝. 수행시간 : "+ (endTime - start) / 1000.0f + "초");
    }
}

class Selection extends Thread{
    int[] a;
    int b;

    Selection(int[] array){
        a = array;
    }

    public void run(){
        System.out.println("선택정렬 시작");
        long start = System.currentTimeMillis();

        for(int i = 0; i < a.length -1; i++){
            for(int j = i+1; j < a.length; j++){
                if(a[i] > a[j]){
                    b = a[j];
                    a[j] = a[i];
                    a[i] = b;
                }
            }
        }
        long endTime = System.currentTimeMillis();

        System.out.println("선택정렬 끝. 수행시간 : " + (endTime - start) / 1000.0f + "초");
    }
}

class Insertion extends Thread{
    int[] a;
    int b;
    int j;

    Insertion(int[] array){
        a = array;
    }

    public void run(){
        System.out.println("삽입정렬 시작");
        long start = System.currentTimeMillis();

        for(int i = 1; i < a.length; i++){
            b = a[i];
            for(j = i-1; j >= 0 && a[j] < b; j--){
                a[j + 1] = a[j];
            }
            a[j + 1] = b;
        }
        long endTime = System.currentTimeMillis();

        System.out.println("삽입정렬 끝. 수행시간 : " + (endTime - start) / 1000.0f + "초");
    }
}




