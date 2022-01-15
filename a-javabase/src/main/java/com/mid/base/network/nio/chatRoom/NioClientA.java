package com.mid.base.network.nio.chatRoom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Nio聊天室客户端
 */
public class NioClientA {
    /**
     * 发送给b需指令:   b:内容
     * @param args
     */
    public static void main(String[] args) {
        // 当前客户端的用户名
        String username = "a";
        // 为当前客户端开辟一个线程
        ClientThread client = new ClientThread(username);
        client.start();

        // 输入输出流
        BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));

        try {
            // 循环读取键盘输入
            String readline;
            while ((readline = sin.readLine()) != null) {
                if (readline.equals("bye")) {
                    client.close();
                    System.exit(0);
                }
                // 发送消息
                client.send(username + ":" + readline);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}