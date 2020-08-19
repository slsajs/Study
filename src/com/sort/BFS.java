package com.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
// 데이터는 1~7까지의 자료를 대상으로 한다
public class BFS {
    static int arr[] = new int[8]; // 정점의 방문여부를 체크할 배열
    static ArrayList<int[]> arrayList = new ArrayList<int[]>(); // 그래프의 표현을 위한 ArrayList

    public static void bfs(int start){ // BFS 메소드 시작 정점을 인자로 받는다
        Queue<Integer> queue = new LinkedList<>(); // 방문했던 정점을 저장하기 위한 Queue

        queue.offer(start); // 시작 정점을 방문했으므로 enQueue
        arr[start] = 1; // 시작 정점을 방문했으므로 1로 체크한다

        while (!queue.isEmpty()){ // 공백Queue가 될 때 까지 반복한다
            int x = queue.poll(); // deQueue연산을 통해 방문했던 정점을 반환한다
            System.out.println(x); // Output
            for (int i = 0; i<arrayList.get(x).length; i++){ // 인접한 정점의 수 만큼 반복
                int y = arrayList.get(x)[i];
                if(!(arr[y] == 1)){ // 인접한 정점을 방문하지 않았다면
                    queue.offer(y); // enQueue연산 후
                    arr[y] = 1; // 방문여부 체크
                }
            }
        }
    }

    public static void main(String[] args) {
        arrayList.add(0, new int[] {0}); // 1~7의 데이터를 사용하므로 0번 인덱스는 0으로 채운다
        arrayList.add(1,new int[] {2,3}); // 1번 정점과 연결 된 정점들
        arrayList.add(2,new int[] {1,3,4,5}); // 2번 정점과 연결 된 정점들
        arrayList.add(3,new int[] {1,2,6,7});
        arrayList.add(4,new int[] {2,5});
        arrayList.add(5,new int[] {2,4});
        arrayList.add(6,new int[] {3,7});
        arrayList.add(7,new int[] {3,6});

        bfs(1); // bfs 호출
    }
}
