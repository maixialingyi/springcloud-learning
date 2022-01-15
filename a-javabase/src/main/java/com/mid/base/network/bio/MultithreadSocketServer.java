package com.mid.base.network.bio;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * 多线程服务端
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

