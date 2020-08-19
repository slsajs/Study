package com.sort;

//이진트리의 순회 알고리즘

class Doubly_List_Node{ // 노드 클래스
    private int data; // 노드의 데이터 필드
    public Doubly_List_Node Llink; // 노드의 왼쪽 링크 필드
    public Doubly_List_Node Rlink; // 노드의 오른쪽 링크 필드

    public Doubly_List_Node(){ // 노드의 기본 생성자
        this.data = 0;
        this.Llink = null;
        this.Rlink = null;
    }
    public Doubly_List_Node(int data){
        this.data = data;
        this.Llink = null;
        this.Rlink = null;
    }
    public Doubly_List_Node(Boolean jud, Doubly_List_Node link){
        if(jud == true){
            this.data = 0;
            this.Llink = link;
            this.Rlink = null;
        }else {
            this.data = 0;
            this.Llink = null;
            this.Rlink = link;
        }
    }
    public Doubly_List_Node(Boolean jud, Doubly_List_Node link, int data){
        if(jud == true){
            this.data = data;
            this.Llink = link;
            this.Rlink = null;
        }else {
            this.data = data;
            this.Llink = null;
            this.Rlink = link;
        }
    }
    public Doubly_List_Node(Doubly_List_Node Llink, Doubly_List_Node Rlink){
        this.data = 0;
        this.Llink = Llink;
        this.Rlink = Rlink;
    }
    public Doubly_List_Node(int data, Doubly_List_Node Llink, Doubly_List_Node Rlink){
        this.data = data;
        this.Llink = Llink;
        this.Rlink = Rlink;
    }
    public int getData(){ // 노드의 데이터를 반환하는 메소드
        return this.data;
    }
    public void setData(int i){
        this.data = i;
    }
}

public class Binarytree {
    public static void preOrder(Doubly_List_Node prenode){ // 이진트리를 전위순회 출력

        if(prenode != null){
            System.out.print(prenode.getData() + " / ");
            preOrder(prenode.Llink);
            preOrder(prenode.Rlink);
        }
    }
    public  static void inOrder(Doubly_List_Node prenode){ // 이진트리를 중위순회 출력

        if(prenode != null){
            inOrder(prenode.Llink);
            System.out.print(prenode.getData() + " / ");
            inOrder(prenode.Rlink);
        }
    }
    public static void postOrder(Doubly_List_Node prenode){ // 이진트리를 후위순회 출력

        if(prenode != null){ // 공백노드가 아니라면
            postOrder(prenode.Llink);
            postOrder(prenode.Rlink);
            System.out.print(prenode.getData() + " / ");
        }
    }

    public static void main(String[] args) {

        Doubly_List_Node arr[] = new Doubly_List_Node[16]; // 15개 데이터

        for(int i =0; i< arr.length; i++){
            arr[i] = new Doubly_List_Node(i);
        }
        for (int i =1; i< arr.length; i++){
            if(i %2 == 0){ // i가 2의 배수일 경우 즉, 짝수일 경우 왼쪽 자식노드에 위치
                arr[i/2].Llink = arr[i];
            }else { // 홀수일 경우 오른쪽 자식노드에 위치하게 된다
                arr[i/2].Rlink = arr[i];
            }
        }
        preOrder(arr[1]);
    }
}
