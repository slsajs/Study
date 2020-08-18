package com.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class ChatServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(10001); // 서버 소켓 인스턴스 생성 소켓넘버를 파라미터로 받고 클라이언트의 접속을 확인해준다
            HashMap<String, Object> hm = new HashMap<String, Object>(); // 해시맵 생성
            while (true){
                System.out.println("접속을 기다립니다");
                Socket sock = server.accept(); // 클라이언트의 접속을 확인하고 동시에 소켓인스턴스를 생성
                ChatThread chatThread = new ChatThread(sock, hm); // 서버 프로그램의 스레드를 생성
                chatThread.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

// ChatThread는 서버 프로그램에서 서버의 일을 대신 해준다. 서버는 그저 while문을 통해 접속하는 클라이언트를 받아주는 역활만을 한다
// 대신 동시에 자신의 일을 대신해줄 수 있는 스레드를 생성하여, 그 스레드에게 서버의 역활을 위임

class ChatThread extends Thread{
    private Socket socket;
    private String id;
    private BufferedReader br;
    private HashMap<String, Object> hm;
    private boolean initFlag = false;

    // 생성자의 파라미터로 소켓 넘버와 해시맵 참조값을 전달
    public ChatThread(Socket socket, HashMap<String,Object> hm){
        this.socket = socket;
        this.hm = hm;
        try {
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream())); // 클라이언트에게 데이터를 전달 해주는 출력 스트림 생성
            br = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 클라이언트로 부터 데이터를 받을 입력스트림 생성
            id = br.readLine(); // 맨 첫번째 배열이었던 id를 읽어와 저장
            broadcast(id + "님이 접속하셨습니다."); // 같이 채팅하는 유저들에게 알림
            System.out.println("접속한 사용자의 아이디 : " + id);

            //해시맵에 id와 서버의 출력스트림 참조값을 저장
            // 만약 스레드가 동시에 해시맵을 참조한다면, 충돌가능성이 있기 때문에 동기화를 시켜준다
            synchronized (hm){
                hm.put(this.id, pw);
            }
            initFlag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String line = null;
            while ((line = br.readLine()) != null){
                if(line.equals("/quit")){ // 사용자가 종료를 선언하는 문자열을 입력했을때 while문을 빠져나옴
                    break;
                }
                if(line.indexOf("/to") == 0){ // 해당 문자열이 첫번째 글자의 위치 번호를 반환 한다. 만약 "/to"가 첫번째에 있다면 "/"의 위치 번호 0을 반환
                    sendmsg(line);
                }else {
                    broadcast(id + " : " + line); // 위의 어느 조건도 만족하지 않는 다면 채팅방에 문자를 출력
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            synchronized (hm){
                hm.remove(id); // 해시맵에 등록된 아이디 삭제
            }
            broadcast(id + "님이 접속을 종료했습니다.");
            try {
                if(socket != null){
                    socket.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }

    // 귓속말 보내는 메소드
    public void sendmsg(String msg){
        int start = msg.indexOf(" ") + 1; // " "이 위치하고 있는 번호에 1을 더해 " " 다음이 시작지점임을 저장, 여기서는 "/to" 부분
        int end = msg.indexOf(" ", start); // start이후 그 다음 " "가 나오는 곳의 위치번호를 저장하여 끝을 알림
        if(end != -1){
            String to = msg.substring(start,end); // 처음번호와 끝번호 사이에 저장되는 문자를 출력
            String msg2 = msg.substring(end +1); // 끝번호 다음 부터 메세지가 입력 되므로 그다음부터 끝까지의 문자열을 msg2에 저장
            Object obj = hm.get(to); // to에 해당하는 데이터 즉 출력스트림의 참조값을 오브젝트 인스턴스에 저장, 모든 클래스가 Object 클래스를 상속하기 때문에 가능
            if(obj != null){
                PrintWriter pw = (PrintWriter)obj; // 해당 참조값을 pw에 저장
                pw.println(id + "님이 다음의 귓속말을 보내셨습니다. : " + msg2); // 해당 출력스트림을 가지고 있는 사람에게 귓속말 전달
                pw.flush();
            }
        }
    }
    public void broadcast(String msg){
        synchronized (hm){
            Collection<Object> collection = hm.values(); // 해시맵에 저장되어 있는 모든 출력스트림의 참조값을 collection에 저장
            Iterator<?> iter = collection.iterator(); // iterator를 생성하여 각각의 데이터를 참조하여 접근하도록 한다

            // 결국 해시맵에 저장되어 있는 모든 사람에게 메세지를 전달하는 것이다
            // 해당 데이터 즉, 출력스트림으로 파라미터로 전달받은 메세지를 전송한다
            while (iter.hasNext()){ // 데이터가 있다면면
               PrintWriter pw = (PrintWriter)iter.next();
                pw.println(msg);
                pw.flush();
            }
        }
    }
}