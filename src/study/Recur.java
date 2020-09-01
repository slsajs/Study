package study;

import java.util.Scanner;

// 재귀 함수 이해하기
public class Recur {

    // 재귀 함수
    static void recur(int n){
        if(n > 0){
            recur(n - 1);
            System.out.println(n);
           // recur(n - 2); // 재귀의 꼬리
            n = n -2; // 재귀의 꼬리 제거
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("정수를 입력하세요 : ");
        int x = sc.nextInt();

        recur(x);
    }
}
