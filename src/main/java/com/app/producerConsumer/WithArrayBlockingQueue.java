package com.app.producerConsumer;

import java.util.concurrent.ArrayBlockingQueue;

public class WithArrayBlockingQueue {

    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
        Producer producer = new Producer(arrayBlockingQueue);
        Consumer consumer = new Consumer(arrayBlockingQueue);
        Thread thread = new Thread(producer);
        Thread thread1 = new Thread(consumer);
        thread.start();
        thread1.start();
    }
}

class Producer implements Runnable {
    ArrayBlockingQueue<Integer> queue;

    Producer(ArrayBlockingQueue<Integer> q) {
        this.queue = q;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            queue.add(i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Consumer implements Runnable {
    ArrayBlockingQueue<Integer> queue;

    Consumer(ArrayBlockingQueue<Integer> q) {
        this.queue = q;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                int value = queue.take();
                System.out.println("value::" + value);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
