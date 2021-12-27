package com.mid.base.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicLong;

public class NIOClient {
    private final Selector selector = Selector.open();
    private AtomicLong atomicLong = new AtomicLong(0);
    private final LinkedBlockingDeque<Long> outgoingQueue = new LinkedBlockingDeque<Long>();

    private Charset charset = Charset.forName("UTF-8");

    public NIOClient(InetSocketAddress addr) throws IOException {
        this.createSocketChannelRegisterAndConnect(addr);
        //开辟一个新线程处理IO读写
        new Reader().start();
    }

    //创建SocketChannel，注册selecter 和 创建连接
    void createSocketChannelRegisterAndConnect(InetSocketAddress addr) throws IOException {
        SocketChannel socketChannel;
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);//设置非阻塞
        socketChannel.socket().setSoLinger(false, -1);
        socketChannel.socket().setTcpNoDelay(true);
        //注册连接事件
        SelectionKey key = socketChannel.register(selector, SelectionKey.OP_CONNECT);
        //发送连接,此处为异步
        socketChannel.connect(addr);
    }

    //sockKey.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
    private class Reader extends Thread {
        public void run() {
            try {
                //轮询
                while(true) {
                    int readyChannels = selector.select();
                    if(readyChannels == 0) continue;

                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
                    while(keyIterator.hasNext()) {
                        SelectionKey selectionKey = keyIterator.next();
                        keyIterator.remove();

                        SocketChannel sc = (SocketChannel)selectionKey.channel();

                        //连接未完成，有可能连接失败，此处要加重连逻辑  暂时不加
                        if(!selectionKey.isConnectable()){
                            break;
                        }

                        if(selectionKey.isReadable()){//事件类型为读取数据
                            ByteBuffer buff = ByteBuffer.allocate(1024);
                            String content = "";
                            while(sc.read(buff) > 0){
                                buff.flip();
                                content += buff.toString();
                            }
                            //继续可以接收读事件
                            selectionKey.interestOps(SelectionKey.OP_READ);
                            System.out.println("Reader 线程读到服务端返回数据："+content);
                        }else if(selectionKey.isWritable()){
                            Long l = atomicLong.incrementAndGet();
                            sc.write(charset.encode(String.valueOf(l)));
                            selectionKey.interestOps(SelectionKey.OP_WRITE);
                            System.out.println("客户端写出 ："+l);
                        }
                    }
                }
            }
            catch (IOException io){

            }
        }
    }

    public static void main(String[] args) throws IOException {
        InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1", 8081);
        new NIOClient(serverAddress);
    }
}
