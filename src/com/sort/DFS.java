package com.sort;

import java.util.ArrayList;

public class DFS {
    static int arr[] = new int[8];
    static ArrayList<int[]> arrayList = new ArrayList<int[]>();

    public static void dfs(int start){ // Stack을 사용하지 않고 컴퓨터 시스템의 기본인 스텍프레임을 이용하므로 재귀함수 만으로 구현가능
        if(arr[start] == 1){ // 재귀 호출을 통해 들어온 정점이 이미 방문한 노드라면 종료
            return;
        }else { // 방문하지 않았다면 방문처리
            arr[start] = 1;
        }
        System.out.println(start);
        for(int i = 0; i<arrayList.get(start).length; i++){ // 인접한 정점의 수 만큼 반복
            int y = arrayList.get(start)[i];
            dfs(y); // 인접한 정점을 만나면 바로 재귀호출을 수행한다
        }
    }

    public static void main(String[] args) {
        arrayList.add(0,new int[] {0});
        arrayList.add(1,new int[] {2,3});
        arrayList.add(2,new int[] {1,3,4,5});
        arrayList.add(3,new int[] {1,2,6,7});
        arrayList.add(4,new int[] {2,5});
        arrayList.add(5,new int[] {2,4});
        arrayList.add(6,new int[] {3,7});
        arrayList.add(7,new int[] {3,6});

        dfs(1);
    }
}
