package com.demo.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lin.wang
 * @date 2020/06/17
 */
public class TestThreadPoolExecutor {
    public static void main(String[] args) {
        BlockingQueue queue = new LinkedBlockingQueue(2);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 6, 1, TimeUnit.SECONDS, queue);

        for (int i = 0; i < 10; i++) {
            System.out.println("i：【" + i + "】，当前线程数量:【" + executor.getPoolSize() + "】" + "  队列长度:【" + queue.size() + "】");
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executor.shutdown();
    }
}
