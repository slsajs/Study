package com.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPTest1 {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket(9999);
            InetAddress ia = InetAddress.getByName("localhost");
            
            while (true){
                // UDP전송
                Scanner sc = new Scanner(System.in);
                if(sc.hasNext()){
                    String sendMsg = sc.nextLine();
                    byte[] bf_send = sendMsg.getBytes();
                    DatagramPacket dp_send = new DatagramPacket(bf_send, bf_send.length, ia, 8888);
                    ds.send(dp_send);
                }

                // UDP 수신
                byte[] bf_recv = new byte[30];
                DatagramPacket dp_recv = new DatagramPacket(bf_recv, bf_recv.length);
                ds.receive(dp_recv);
                String rs1 = new String(dp_recv.getData());
                System.out.println("소스 주소 : " + dp_recv.getAddress() + ":" + dp_recv.getPort());
                System.out.println("수신메시지 : " + rs1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
