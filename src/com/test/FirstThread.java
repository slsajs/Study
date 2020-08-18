package com.test;

public class FirstThread extends Thread {
    private String word;
    private int time;
    private int count;

    public FirstThread(String word, int time, int count) {
        this.word = word;
        this.time = time;
        this.count = count;
    }

    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.print(word);
            try {
                Thread.sleep(time);
            } catch (Exception e) {
            }
        }
    }


    public static void main(String[] args) {
        FirstThread tick = new FirstThread("tick",1000,4);
        FirstThread tack = new FirstThread("tack\n", 1000, 3);

        tick.start();
        try {
            Thread.sleep(500);
        }catch (Exception e){

        }tack.start();
    }
}
