package com.etc;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapHeader;
import org.jnetpcap.PcapIf;
import org.jnetpcap.nio.JBuffer;
import org.jnetpcap.nio.JMemory;
import org.jnetpcap.packet.JRegistry;
import org.jnetpcap.packet.Payload;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;

import java.util.ArrayList;

public class PcapTest {
    public static void main(String[] args) {

        //디바이스를 담을 변수를 ArrayList로 생성
        ArrayList<PcapIf> allDevs = new ArrayList<PcapIf>();

        //에러처리
        StringBuilder errbuf = new StringBuilder();

        //접근가능한 디바이스를 첫 번째인자에 담는다, 두 번째 인자는 에러처리
        int r = Pcap.findAllDevs(allDevs, errbuf);

        if(r == Pcap.NOT_OK || allDevs.isEmpty()){
            System.out.println("네트워크 장치 찾기 실패." + errbuf.toString());
            return;
        } //예외처리

        System.out.println("< 탐색된 네트워크 Device >");
        int i = 0; //Device Numbering 용도

        for (PcapIf device : allDevs){
            //탐색한 장비를 출력
            String description = (device.getDescription() != null) ? device.getDescription() : "장비에 대한 설명이 없습니다.";
            System.out.printf("[%d번]: %s [%s]\n", ++i, device.getName(), description);
        }

        PcapIf device = allDevs.get(0);
        System.out.printf("선택된 장치: %s\n", (device.getDescription() != null) ?
                device.getDescription() : device.getName());

        int snaplen = 64 * 1024; //65536Byte만큼 패킷을 캡처
        int flags = Pcap.MODE_NON_PROMISCUOUS; //무차별모드
        int timeout = 10 * 1000; // timeout 10second
        Pcap pcap = Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);

        if(pcap == null){
            System.out.printf("Network Device Access Failed. Error:" + errbuf.toString());
            return;
        }

        //여기서 부터 계층별 객체 생성
        Ethernet eth = new Ethernet();
        Ip4 ip = new Ip4();
        Tcp tcp = new Tcp();
        Payload payload = new Payload();
        PcapHeader header = new PcapHeader(JMemory.POINTER);
        JBuffer buf = new JBuffer(JMemory.POINTER);

        int id = JRegistry.mapDLTToId(pcap.datalink()); //pcap의 datalink 유형을 jNetPcap의 프로토콜 ID에 맵핑

        while (pcap.nextEx(header,buf) == Pcap.NEXT_EX_OK){ //헤더와 버퍼를 피어링
            PcapPacket packet = new PcapPacket(header, buf);

            packet.scan(id); //새로운 패킷을 스캔하여 포함 된 헤더를 찾습니다.
            System.out.printf("[%d]\n", packet.getFrameNumber());
            System.out.println("################################## Packet ##################################");

            if(packet.hasHeader(eth)){
                System.out.printf("출발지 MAC 주소 = %s\n도착지 MAC 주소 = %s\n",
                        FormatUtils.mac(eth.source()), FormatUtils.mac(eth.destination()));
            }
            if(packet.hasHeader(ip)){
                System.out.printf("출발지 IP 주소 = %s\n도착지 IP 주소 = %s\n",
                        FormatUtils.ip(ip.source()), FormatUtils.ip(ip.destination()));
            }
            if(packet.hasHeader(tcp)){
                System.out.printf("출발지 TCP 주소 = %d\n도착지 TCP 주소 = %d\n",
                        tcp.source(), tcp.destination());
            }
            if(packet.hasHeader(payload)){
                System.out.printf("페이로드의 길이 = %d\n", payload.getLength());
                System.out.print(payload.toHexdump()); //와이어샤크에서 보이는 hexdump를 출력
            }
        }
        pcap.close();
    }
}
