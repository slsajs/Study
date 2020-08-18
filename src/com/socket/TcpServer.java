package com.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Logger;

public class TcpServer {

    private static BufferedReader reader;
    public static final int PORT = 13;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                try (Socket socket = server.accept()) {
                    Writer out = new OutputStreamWriter(socket.getOutputStream());
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    getMessage();
                    Date now = new Date();
                    out.write(now.toString() + "\r\n");
                    out.flush();
                } catch (IOException e) {
                    System.err.println("데이터를 전송하는 중에 문제가 발생");
                }
            }

        } catch (IOException e) {
            System.err.println("연결을 맺는 중에 문제가 발생");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void getMessage() {
        try {
            while (true) {
                System.out.println("클라이언 : " + reader.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}