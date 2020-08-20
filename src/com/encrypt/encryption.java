package com.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.*;
import java.sql.SQLOutput;
import java.util.Base64;
//https://offbyone.tistory.com/286 참고 블로그
public class encryption{

    public static String byteToHexString(byte[] data) {
        // StringBuilder : 문자열의 저장 및 변경을 위한 메모리 공간을 지닌 클래스, 단일 스레드 환경에서만 사용 가능
        // StringBuffer : 동시에 처리하는것을 허용 하기 때문에 멀티 쓰레드 프로그래밍에서는 Buffer를 사용
        // 문자열 데이터의 추가를 위해 append와 insert메소드를 지니고있다.
        StringBuilder sb = new StringBuilder();
        for(byte b : data){
            //value & 0xff(255) : byte 변수는 -127 ~ 127 범위를 표현하므로 127를 넘어가면 음수로 표출된다 이를 방지하기 위해 type을 int형으로 변환
            // https://javaslave.tistory.com/59 밑에 내용 설명
            // 0x100강제로 3자리수로 변경 1 -> 101 후 substring 100 -> 01로
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1)); // 정수값을 16진수로 변환 해주는 코드
        }
        return sb.toString();
    }

    public static String encryptAES256(String msg, String key) throws Exception{
        SecureRandom random = new SecureRandom();
        //난수 바이트 생성
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        byte[] saltBytes = bytes;

        //PBEKeySpec을 실제 키로 사용하기 위해서는 SecretKeyFactory의 generateSecret() 메소드와 함께 사용해야 한다.
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1"); // getInstance() 메소드 인자 : AES, ARCFOUR, DES, DESede, PBKDF2WithHmacSHA1
        //PBEKeySpec : 패스워드 베이스의 암호화 (PBE)로 사용할 수 있는 사용자가 선택한 패스워드입니다.
        PBEKeySpec spec = new PBEKeySpec(key.toCharArray(), saltBytes, 70000, 256);// 7000번 해시하여 256bit 길이의 키를 만든다.

        SecretKey secretKey = factory.generateSecret(spec); //지정된 key 스펙 (key 데이터)으로부터 SecretKey 객체를 생성합니다.
        SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");

        //AES Cipher 객체 생성
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 알고리즘/운용모드/패딩
        //암호화 Cipher 초기화
        cipher.init(Cipher.ENCRYPT_MODE,secret);

        //암호화 매개 변수의 불투명 한 표현으로 사용됩니다
        AlgorithmParameters params = cipher.getParameters();
        //Initial Vector(1단계 암호화 블록용)
        byte[] ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();

        //암호화 완료
        byte[]encryptedTextBytes = cipher.doFinal(msg.getBytes("UTF-8"));

        byte[] buffer = new byte[saltBytes.length + ivBytes.length + encryptedTextBytes.length];
        //배열의 값을 복사
        System.arraycopy(saltBytes, 0, buffer, 0, saltBytes.length);
        System.arraycopy(ivBytes, 0, buffer, saltBytes.length, ivBytes.length);
        System.arraycopy(encryptedTextBytes, 0, buffer, saltBytes.length + ivBytes.length, encryptedTextBytes.length);

        //Base64는 64문자의 영숫자를 이용하여 멀티 바이트 문자열이나 이진 데이터를 다루기 위한 인코딩 방식
        return Base64.getEncoder().encodeToString(buffer);

    }

    public static String decryptAES256(String msg, String key) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        ByteBuffer buffer = ByteBuffer.wrap(Base64.getDecoder().decode(msg));

        byte[] saltBytes = new byte[20];
        buffer.get(saltBytes, 0, saltBytes.length);
        byte[] ivBytes = new byte[cipher.getBlockSize()];
        buffer.get(ivBytes,0, ivBytes.length);
        byte[] encryoptedTextBytes = new byte[buffer.capacity() - saltBytes.length - ivBytes.length];
        buffer.get(encryoptedTextBytes);

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        PBEKeySpec spaec = new PBEKeySpec(key.toCharArray(), saltBytes, 70000, 256);

        SecretKey secretKey = factory.generateSecret(spaec);
        SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(),"AES");

        cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(ivBytes));

        byte[] decryptedTextBytes = cipher.doFinal(encryoptedTextBytes);
        return new String(decryptedTextBytes);
    }

    public  static String sha256(String msg) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(msg.getBytes());
        return byteToHexString(md.digest());
    }

    public static String md5(String msg) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(msg.getBytes());
        return byteToHexString(md.digest());
    }

    public static void main(String[] args) throws Exception {
        String plainText = "Hello, World!";
        String key = "secret key";

        System.out.println("MD5 :" + plainText + " - " + md5(plainText));
        System.out.println("SHA-256 :" + plainText + " - " + sha256(plainText));

        String encrypted = encryptAES256("Hello, World!", key);

        System.out.println("AES-256 : enc - " + encrypted);
        System.out.println("AES-256 : dec - " + decryptAES256(encrypted, key));
    }
}

