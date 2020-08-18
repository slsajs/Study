package com.test;

import java.io.*;

public class test1 {
    public static void main(String[] args) {
        try {
            String filename = "file.dat";
            FileOutputStream out = new FileOutputStream(filename);
            FileInputStream file = new FileInputStream(filename);
            InputStreamReader in = new InputStreamReader(file);

            for(byte i = 1; i <= 10; i++){
                out.write(i);
            }
            int c;
            while ((c=in.read()) != -1){
                System.out.println(c + " ");

            }
            in.close();
            out.close();
           /* FileOutputStream out  = new FileOutputStream("file.dat");
            out.write(65);
            out.close();*/
        /*    FileWriter out = new FileWriter("math.txt");
            int a = 10, b = 5;
            out.write("덧셈:");
            out.write(a + "+" + b + " = " + (a+b) + "\n");
            out.write("뺄셈:");
            out.write(a + " - " + b + " = " + (a-b) + "\n");
            out.close();*/
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
