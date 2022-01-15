package com.mid.base.network.nio.chatRoom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Nio聊天室客户端线程
 */
public class ClientThread extends Thread {
    /**
     * 解密
     */
    private CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();

    /**
     * 加密
     */
    private CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();

    /**
     * 选择器
     */
    private Selector selector = null;

    /**
     * 通道
     */
    private SocketChannel socket = null;

    /**
     * 通道key
     */
    private SelectionKey clientKey = null;

    /**
     * 用户名
     */
    private String username;
    // 可写事件就绪后内容
    private AtomicLong atomicLong = new AtomicLong(0);

    public ClientThread(String username) {
        try {
            // 创建一个Selector
            selector = Selector.open();
            // 创建Socket并注册
            socket = SocketChannel.open();
            //设置非阻塞
            socket.configureBlocking(false);
            socket.socket().setSoLinger(false, -1);
            socket.socket().setTcpNoDelay(true);
            clientKey = socket.register(selector, SelectionKey.OP_CONNECT);

            // 连接到远程地址
            InetSocketAddress ip = new InetSocketAddress("localhost", 8080);
            socket.connect(ip);

            this.username = username;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开辟读取事件的线程
     */
    @Override
    public void run() {
        try {
            // 监听事件（无限循环）
            while (true) {
                // 监听事件
                int readyChannels = selector.select();
                if(readyChannels == 0) continue;
                // 事件来源列表
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey selectionKey = it.next();
                    // 删除当前事件
                    it.remove();

                    SocketChannel channel = (SocketChannel) selectionKey.channel();

                    // 判断事件类型
                    if (selectionKey.isConnectable()) {
                        // 连接事件
                        if (channel.isConnectionPending())
                            channel.finishConnect();
                        //channel.register(selector, SelectionKey.OP_READ);
                        selectionKey.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        System.out.println("连接服务器端成功！");

                        // 发送用户名
                        send("username=" + this.username);
                    } else if (selectionKey.isReadable()) {
                        // 读取数据
                        ByteBuffer buffer = ByteBuffer.allocate(50);
                        channel.read(buffer);
                        buffer.flip();
                        String msg = decoder.decode(buffer).toString();
                        //继续可以接收读事件
                        selectionKey.interestOps(SelectionKey.OP_READ);
                        System.out.println("收到：" + msg);
                    }  /*else if(selectionKey.isWritable()){
                        // zk是读取队列后才发送
                        Long l = atomicLong.incrementAndGet();
                        channel.write(encoder.encode(CharBuffer.wrap(username + ":客户端可写监听时发送-" +  atomicLong)));
                        selectionKey.interestOps(SelectionKey.OP_WRITE);
                    }*/
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭
            try {
                selector.close();
                socket.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 发送消息
     *
     * @param msg
     */
    public void send(String msg) {
        try {
            SocketChannel client = (SocketChannel) clientKey.channel();
            client.write(encoder.encode(CharBuffer.wrap(msg)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭客户端
     */
    public void close() {
        try {
            selector.close();
            socket.close();
        } catch (IOException e) {
        }
    }
}