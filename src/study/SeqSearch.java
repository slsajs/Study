package study;

import java.util.Scanner;

//선형 검색
public class SeqSearch {
    // 요솟수가 n인 배열 a에서 key와 같은 요소를 선형 검색 합니다.
    static int seqSearch(int[] a, int n, int key){

        for(int i = 0; i < n; i++){
            if(a[i] == key){
                return i;
            }
        }
        return -1;

        //while문
   /*        int i =0;
       while (true){
            if(i == n){
                return -1; // 검색 실패(요솟수랑 i랑 크기가 같아지면 검색을 못한것)
            }
            if(a[i] == key){
                return i; // 검색 성공(a[i]에서 찾고자하는 key와 같은 같이 있으면 해당 인덱스 반환
            }
            i++;
        }*/
    }

    static void searchIdx(int[] a, int n, int key, int[] idx){
        int b = 0;
        for (int i = 0; i < n; i++){
            if(a[i] == key){
                idx[b] = i;
                b++;
            }
        }
        for (int i = 0; i < idx.length; i++){
            System.out.print(idx[i]);
        }
/*        int i = 0;
        while (true){
            if(a[i] == key){
                idx[i] = i;
            }else {
                break;
            }
        }*/

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("요솟수 : ");
        int num = sc.nextInt();
        int[] x = new int[num]; // 요솟수가 num인 배열
        int[] in = new int[3];
        for (int i = 0; i < num; i++){
            System.out.print("x[" + i + "] : ");
            x[i] = sc.nextInt();
        }
        System.out.print("검색할 값 : ");
        int ky = sc.nextInt(); // 키 값을 입력

        int idx = seqSearch(x, num, ky); // 배열 x에서 키 값이 ky인 요소를 검색
        searchIdx(x, num, ky, in);

        if(idx == -1){
            System.out.println("그 값의 요소가 없습니다.");
        }else {
            System.out.println("\n" + ky + "은(는) x[" + idx + "]에 있습니다.");
        }
    }
}
