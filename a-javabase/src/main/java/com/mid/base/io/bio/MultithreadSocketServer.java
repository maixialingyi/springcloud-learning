package com.mid.base.io.bio;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author jiangshaoyue
 * @Date 2019/7/17 17:49
 */
public class MultithreadSocketServer {

    public static void main(String args[]) throws Exception {
        ServerSocket server = new ServerSocket(80);
        while (true) {
            Socket socket = server.accept();
            new Thread(new SocketHandlerThread(socket)).start();
        }
    }
}

