package com.test;

import java.util.ArrayList;
import java.util.List;

public class GenericSample {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<Integer>();

        for(int i =0; i < 5; i++){
            list.add(i * 3);
        }
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }
}
