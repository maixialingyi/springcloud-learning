package com.mid.base.io.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Author jiangshaoyue
 * @Date 2019/7/18 10:18
 */
public class SocketHandlerThread implements Runnable{

    private Socket socket;

    public SocketHandlerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            while (true){
                String body = in.readLine();
                if(body == null){
                    break;
                }
                System.out.println("客户端发来消息 : "+ body);
                out.println(System.currentTimeMillis());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
