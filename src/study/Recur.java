package study;

import java.util.Scanner;

// 재귀 함수 이해하기
public class Recur {

    // 재귀 함수(1차)
   /* static void recur(int n){
        if(n > 0){
            recur(n - 1);
            System.out.println(n);
           // recur(n - 2); // 재귀의 꼬리
            n = n -2; // 재귀의 꼬리 제거
        }
    }*/
    // stack을 이용한 재귀 꼬리 제거
    static void recur(int n){
        IntStack s = new IntStack(n);

        while (true) {
          if(n > 0) {
              s.push(n);  // n의 값을 푸시
              n = n - 1;
              continue;
          }
            if (s.isEmpty() != true) { // 스택이 비어 있지 않다면
                n = s.pop();         // 저장하고 있던 스택의 값을 팝
                System.out.println(n);
                n = n - 2;
                continue;
            }
            break;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("정수를 입력하세요 : ");
        int x = sc.nextInt();

        recur(x);
    }
}
