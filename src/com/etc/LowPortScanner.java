package com.etc;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class LowPortScanner {
    public static void main(String[] args) {
        String host = "127.0.0.1";

        for(int i=1; i<1024; i++){
            try {
                Socket socket = new Socket(host, i);

                System.out.println("There is a server on port " + i + " of " + host);
                socket.close();
            }catch (UnknownHostException e){
                e.printStackTrace();
                break;
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
