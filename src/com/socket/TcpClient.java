package com.socket;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TcpClient {

    public static void main(String[] args) {
        String host = "localhost";

        try {
            Socket socket = new Socket(host, 13);

            InputStream in = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(in, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            String line;
            Scanner sc = new Scanner(System.in);
            while (true){//(line=br.readLine()) != null
               // System.out.println(line);
                writer.println(sc.next());
            }

        //    System.out.println("There is a server on port 13 of " + host);
        //    br.close();
        //    socket.close();
        }catch (UnknownHostException e){
            System.err.println(e);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
