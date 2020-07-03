package com.demo.thread;

/**
 * @author lin.wang
 * @date 2020/06/16
 */
public class TestThread implements Runnable {
    /**
     * 使用静态成员变量作为100张票的保存变量，是一个共享资源。
     * 不加static则每一个实例都有tickets张变量
     */
    private static int tickets = 100;

    @Override
    public void run() {
        // 完成售票过程
        while (true) {
            // 字符串可以作为锁对象，因为双引号包含的字符串不管在代码中如何运行，有且只有一个
            // TestThread.class 只有一个，所以同步
            synchronized (this) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (tickets > 0) {
                    System.out.println(Thread.currentThread().getName() + "售出了" + tickets + "张票");
                    tickets--;
                } else {
                    System.out.println(Thread.currentThread().getName() + "售罄！！！");
                    break;
                }
            }
        }
    }
}

class Demo {
    public static void main(String[] args) {
        // 这样写   synchronized (this) 不只有一个，所以异步，每个线程都是一样票
//        Thread t1 = new Thread(new TestThread(), "售票人员1");
//        Thread t2 = new Thread(new TestThread(), "售票人员2");
//        Thread t3 = new Thread(new TestThread(), "售票人员3");
//        t1.start();
//        t2.start();
//        t3.start();


        // 这样写   synchronized (this) 只有一个，所以同步
        TestThread myRunnable = new TestThread();
        for (int i = 1; i <= 3; i++) {
            new Thread(myRunnable, "售票人员" + i).start();
        }
    }
}
