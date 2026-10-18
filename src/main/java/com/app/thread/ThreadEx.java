package com.app.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadEx {
    volatile static boolean x= true;
    static final CountDownLatch latch = new CountDownLatch(1);
    public static void main(String[] args) {
        ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();
        service.submit(task);
        service.submit(task2);
        System.out.println("Hello main"+Thread.currentThread().getName());
    }

    static Runnable task= ()->{
        System.out.println("Hello"+x);
       // if(x){
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("task"+x);
            outer();
           // x=false;
       // }
    };

    static Runnable task2= ()->{
        System.out.println("Hello"+x);
        if(x){
            System.out.println("task2"+x);

            x=false;
            latch.countDown();
           // inner();

        }
    };

    static synchronized void outer(){
        System.out.println("Hello from outer");
        inner();
    }
    static synchronized void inner(){
        System.out.println("Hello from inner");
    }



}
