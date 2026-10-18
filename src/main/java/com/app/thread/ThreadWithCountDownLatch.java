package com.app.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadWithCountDownLatch {
    static volatile boolean flag = true;
    static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {

        ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();
        service.submit(task);
        service.submit(task2);

    }

    static Runnable task = () -> {
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(flag + "in task");
    };

    static Runnable task2 = () -> {
        if (flag) {
            latch.countDown();
            flag = false;
            System.out.println(flag + "in task2");
        }
    };
}
