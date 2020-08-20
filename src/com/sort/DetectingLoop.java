package com.sort;

import org.w3c.dom.Node;

import java.util.Objects;

// 토끼와 거북이 알고리즘
public class DetectingLoop {

    Node head;

    class Node{ // 노드 클래스
        int data;
        Node next;

        Node(int i){
            this.data = i;
        }
    }
    public void add(int i){ // 리스트에 노드 추가 함수
        if(head == null){
            head = new Node(i);
        }else {
            Node temp = head;
            while (temp.next != null){
                temp = temp.next;
            }
            temp.next = new Node(i);
        }
    }

    public void print(){ // 리스트 출력 함수
        Node temp = head;
        while (temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public void makeLoop(){ // 루프를 만드는 메소드
        Node temp = head;
        Node temp1 = head;

        for(int i = 0; i < 2; i++){
            temp = temp.next;
        }
        while (temp1.next != null){
            temp1 = temp1.next;
        }
        temp1.next = temp;
    }

    public boolean detect(){ // 루프를 탐지하는 메소드
        Node hare = head;
        Node tortoise = head;

        while (true){
            if(hare.next == null){
                return false;
            }
            hare = hare.next;

            if(hare.next == null){
                return false;
            }
            hare = hare.next;
            tortoise = tortoise.next;

            if(hare == tortoise){
                return true;
            }
        }
    }

    public static void main(String[] args) {
        DetectingLoop hello = new DetectingLoop();
        hello.add(1);
        hello.add(2);
        hello.add(3);
        hello.add(4);
        hello.add(5);
        hello.add(6);
        hello.add(7);
        hello.add(8);
        hello.print();
        hello.makeLoop();
        System.out.println(hello.detect());
    }
}
