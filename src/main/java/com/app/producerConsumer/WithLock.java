package com.app.producerConsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WithLock {
    public static void main(String[] args) {
        CustomBucketLock<Integer> bucket= new CustomBucketLock<>(5);
        Thread t1= new Thread(()->{
            for(int i=0;i<5;i++){
                try {
                    bucket.produce(i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        Thread t2= new Thread(()->{
            for(int i=0;i<5;i++){
                try {
                    bucket.consume();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        t1.start();t2.start();
    }

}

class CustomBucketLock<T> {
    Queue<T> queue= new LinkedList<>();
    int capacity;
    Lock lock = new ReentrantLock();
    Condition notEmpty = lock.newCondition();
    Condition notFull= lock.newCondition();

    CustomBucketLock(int capacity){
        this.capacity= capacity;
    }

    public void produce(T i) throws InterruptedException {
        try {
            lock.lockInterruptibly();
            while (queue.size() == capacity) {
                notEmpty.wait();
            }
            queue.add(i);
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }


    public T consume() throws InterruptedException {
        T item=null;
        try {
            lock.lockInterruptibly();
            while (queue.isEmpty()) {
                notFull.wait();
            }
            item=queue.poll();
            System.out.println("item is"+item);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
        return item;
    }


}
