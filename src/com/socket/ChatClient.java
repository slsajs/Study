package com.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) {
        if(args.length != 2){
            System.out.println("사용법 : java chatclient id 접속할 서버 ip");
            System.exit(1); //id와 ip를 적으면 배열 개수가 2가 되기 떄문에 만약 2가 아니라면 잘못입력 된 것이므로 프로그램을 종료
        }
        Socket socket = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        boolean endflag = false;
        try {
            socket = new Socket(args[1], 10001); //args[1]은 서버 ip로 소켓에 ip주소 전달
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream())); //서버에 데이터를 전달할 수 있는 스트림을 생성한다
            br = new BufferedReader(new InputStreamReader(socket.getInputStream())); //서버로부터 데이터를 받아 올 수 있는 스트림을 생성한다
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in)); //사용자가 콘솔상에 직접 입력하는 스트림을 생성

            //사용자 id를 전송
            pw.println(args[0]); //서버로 사용자 id전송
            pw.flush(); // 버퍼 비우기
            InputThread it = new InputThread(socket, br); //클라이언트의 스레드, 데이터를 받으면서 동시에 문자를 입력하도록 한다
            it.start(); //스레드 시작
            String line = null;
            while ((line = keyboard.readLine()) != null){ //사용자가 입력한 물자열을 입력받아서 서버로 전송하는 역활
                pw.println(line); //pw 서버로 이어지는 출력 스트림
                pw.flush();
                if(line.equals("/quit")){ //만약 사용자가 /quit를 입력했다면 endflag를 true로 바꾸고 while문 빠져나옴
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

// InputThread는 클라이언트 프로그램이 입력을 하는 동시에 데이터를 받을 수 있게 해준다
// 이 스레드는 들어오는 데이터를 계속해서 받아주는 역활을 한다

class InputThread extends Thread{
    private Socket socket = null;
    private BufferedReader br = null;

    public InputThread(Socket socket, BufferedReader br){ //생성과 동시에 소켓 넘버와 서버로부터 데이터를 읽어오는 입력 스트림의 주소를 받는다
        this.socket = socket;
        this.br = br;
    }
    public void run(){
        try {
            String line = null;
            while ((line = br.readLine()) != null){ // 서버로부터 데이터를 읽음과 동시에 화면에 출력해준다
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