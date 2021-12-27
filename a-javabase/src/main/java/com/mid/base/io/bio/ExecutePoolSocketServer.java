package com.mid.base.io.bio;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author jiangshaoyue
 * @Date 2019/7/18 9:56
 */
public class ExecutePoolSocketServer {

    public static void main(String args[]) throws Exception {
        ServerSocket server = new ServerSocket(80);
        ExecutePoolHandler executePoolHandler = new ExecutePoolHandler(50,10000);//创建线程池
        while (true) {
            Socket socket = server.accept();
            executePoolHandler.execute(new SocketHandlerThread(socket));
        }
    }
}

class ExecutePoolHandler {

    private ExecutorService executorService;

    public ExecutePoolHandler(int maxPoolSize, int queueSize) {
        executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public void execute(Runnable task) {
        executorService.execute(task);
    }
}
