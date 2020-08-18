package com.test;

import java.io.*;

public class test {
    public static void main(String[] arg){
       try {
           BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
           int a, b = 7;
           System.out.println("이름을 입력하세요");
           String name = in.readLine();
           System.out.println("0에서 9까지 숫자를 입력하세요");
           String c = in.readLine();
           a = Integer.parseInt(c);
           while (a != b) {
               if ((a == b - 1) || (a == b + 1)) {
                   System.out.println("아깝");
               } else if (a > b + 1) {
                   System.out.println("좀더 작은 수");
               } else if (a < b - 1) {
                   System.out.println("좀더 큰 수");
               }
               c = in.readLine();
               a = Integer.parseInt(c);
           }
           System.out.println("정답 입니다" + name);
       }catch (IOException e){
           e.printStackTrace();
       }
         /*   FileReader in = new FileReader("C:\\Users\\kimminsu\\Desktop\\팀장님 ppt.txt");
            int c;
            String s = new String();
            while ((c = in.read()) != -1);
            s = s + (char) c;
            System.out.print(s);
            in.close();*/
      /*  }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException ie){
            System.out.println("파일이 존재하지 않습니다.");
        }*/



    }
}