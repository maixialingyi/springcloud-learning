package com.mid.base.io.nio.bufferTest;

import java.nio.ByteBuffer;

/**
 * @Author jiangshaoyue
 * @Date 2019/6/25 9:45
 */
public class ByteBufferTest {

    public static void main(String args[]){
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[]{1,2,3});
        System.out.println(byteBuffer.isDirect());

        System.out.println(ByteBuffer.allocateDirect(1000).isDirect());
        System.out.println(ByteBuffer.allocate(100).isDirect());

    }
}
