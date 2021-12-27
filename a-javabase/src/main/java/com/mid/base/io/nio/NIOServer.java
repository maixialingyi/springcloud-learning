package com.mid.base.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    //selector
    private static  Selector selector;
    //字符编码
    private Charset charset = Charset.forName("UTF-8");

    public NIOServer(InetSocketAddress address){
        try {
            //获取selector
            selector = Selector.open();
            //监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel
            ServerSocketChannel chanel = ServerSocketChannel.open();
            //绑定地址和端口
            chanel.bind(address);
            //设置为非阻塞的，jdk为了兼容，默认为阻塞的
            chanel.configureBlocking(false);
            //将ServerSocketChannel注册到selector上，感兴趣的事件为接收连接
            chanel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("[系统消息提示]NIO服务器初始化完毕，监听端口" + address.getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //监听客户端连接
    public void listen(){
        try {
            while (true){
                //这个方法可能会阻塞，直到至少有一个已注册的事件发生,或者当一个或者更多的事件发生时
                //selector.select(long timeout);可以设置超时时间，防止进程阻塞
                //selector.wakeup();可以唤醒阻塞状态下的selector
                //selector.selectNow();也可以立刻返回，不阻塞
                int select = selector.select();
                if(select == 0){
                    continue;
                }
                //返回就绪事件列表 SelectionKey包含事件信息
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    //处理事件业务
                    processKey(selectionKey);
                    //移除事件 因为selector不会自动移除，如果不收到移除，下次selectedKeys()还会继续存在该selectionKey
                    iterator.remove();
                }
            }
        }catch (Exception e){

        }

    }

    private void processKey(SelectionKey selectionKey) throws IOException {
        if(selectionKey.isAcceptable()){//事件类型为接受连接
            //获取对应的ServerSocketChannel
            ServerSocketChannel server = (ServerSocketChannel)selectionKey.channel();
            //为每一个连接创建一个SocketChannel，这个SocketChannel用来读写数据
            SocketChannel client = server.accept();
            //设置为非阻塞
            client.configureBlocking(false);
            //注册selector 感兴趣事件为读数据，意思就是客户端发送写数据时，selector就可以接收并读取数据
            client.register(selector, SelectionKey.OP_READ);
            //继续可以接收连接事件
            selectionKey.interestOps(SelectionKey.OP_ACCEPT);
            System.out.println("服务端接收到连接事件");
        }else if(selectionKey.isReadable()){//事件类型为读取数据
            //得到SocketChannel
            SocketChannel client = (SocketChannel)selectionKey.channel();
            //定义缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            String content = "";
            while (client.read(buffer) > 0){
                //buffer由写模式变成读模式，因为client.read(buffer)是从管道写数据到缓冲区中
                buffer.flip();
                content += charset.decode(buffer);
            }
            //清空缓冲区
            buffer.clear();
            //继续注册读事件类型
            selectionKey.interestOps(SelectionKey.OP_READ);
            System.out.println("服务端接收到信息 : "+content);
            //业务处理
            //1.返回客户端内容
            client.write(charset.encode(content));
            //2.发送消息到其他客户端   广播数据到所有的SocketChannel中
            /*for(SelectionKey key : selector.keys()) {
                java.nio.channels.Channel targetChannel = key.channel();
                //排除客户端自身
                if(targetChannel instanceof SocketChannel && targetChannel != client) {
                    SocketChannel target = (SocketChannel)targetChannel;
                    target.write(charset.encode(content.toString()));
                }
            }*/
        }
    }


    public static void main(String[] args) {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8081);
        new NIOServer(address).listen();
    }
}
