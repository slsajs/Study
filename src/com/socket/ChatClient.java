package com.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) {
/*        if(args.length != 2){
            System.out.println("사용법 : java chatclient id 접속할 서버 ip");
            System.exit(1);
        }*/
        Socket socket = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        boolean endflag = false;
        try {
            socket = new Socket("localhost", 10001); // 아이디, 포트
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        //    pw.println(args[0]);
            pw.flush();
            InputThread it = new InputThread(socket, br);
            it.start();
            String line = null;
            while ((line = keyboard.readLine()) != null){
                pw.println(line);
                pw.flush();
                if(line.equals("/quit")){
                    endflag = true;
                    break;
                }
            }
            System.out.println("클라이언트 접속 종료");
    }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (pw != null){
                    pw.close();
                }
                if(br != null){
                    br.close();
                }
                if(socket != null){
                    socket.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

class InputThread extends Thread{
    private Socket socket = null;
    private BufferedReader br = null;

    public InputThread(Socket socket, BufferedReader br){
        this.socket = socket;
        this.br = br;
    }
    public void run(){
        try {
            String line = null;
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(br != null){
                    br.close();
                }
                if(socket != null){
                    socket.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}