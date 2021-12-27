package com.mid.base.cupjvm;

import lombok.SneakyThrows;

public class CpuJvmTeat {

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while(true){
                    Thread.sleep(20);
                    System.out.println("A");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while(true){
                    Thread.sleep(20);
                    System.out.println("B");
                }
            }
        }).start();

        Thread.sleep(10000000000000L);
    }
}

