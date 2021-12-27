package com.mid.base.jdkProxy.hasextends;

public class RealSubject implements ISubject {

    @Override
    public void pull() {
        System.out.println("pull");
    }

    @Override
    public void push() {
        System.out.println("push");
    }
}
