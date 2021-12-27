package com.mid.base.io.bio;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Author jiangshaoyue
 * @Date 2019/7/18 9:22
 */
public class SocketClient {
    public static void main(String args[]) throws Exception {
        Socket socket = new Socket("127.0.0.1", 80);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        for (; ; ) {
            Thread.sleep(1000);
            out.println(JSON.toJSONString(new User(System.currentTimeMillis(),"姜绍月")));
            String resp = in.readLine();
            System.out.println("服务端返回消息 : " + resp);
        }
    }


}
