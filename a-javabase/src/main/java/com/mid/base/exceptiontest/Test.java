package com.mid.base.exceptiontest;

import java.io.IOException;

public class Test {

    public void oneNullEx(){
        if(1==1){
            throw new TestNullPorintException();
        }
    }

    public void twoNullEx(){
        this.oneNullEx();
    }

    public void oneEx() throws TestException {
        if(1==1){
            throw new TestException();
        }
    }

    public void onefinally() throws IOException {
        try{
            if(1==1){
                throw new IOException();
            }
        }catch (Exception e){
            throw new IOException();
        }finally {
            System.out.println("============");
        }
    }

    public void twofinally() throws IOException {
        try{
            if(1==1){

            }
       }finally {
            System.out.println("============");
        }
    }
    public int threefinally() {
        int a = 0;
        try{
            return 1;
        }catch (Exception e){
            return 3;
        } finally{
            System.out.println("----");        }
    }
    public static void main(String[] args) throws IOException {
        Test test = new Test();
        System.out.println(test.threefinally());

    }
}
