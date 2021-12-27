package com.mid.base;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        /*List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Test test = new Test();
        int serverCount = list.size();

        for (;;) {
            int current = test.atomicInteger.get();
            System.out.println(current);
            int next = (current + 1) % serverCount;
            System.out.println(next);
            if (test.atomicInteger.compareAndSet(current, next)){
                System.out.println("-----"+next);
                return;
            }
        }*/
        System.out.println(1111111112%1000000);
        System.out.println(22%10);
    }
}
