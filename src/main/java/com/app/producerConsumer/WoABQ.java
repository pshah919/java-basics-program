package com.app.producerConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class WoABQ {

    public static void main(String[] args) {
        CustomBucket customBucket = new CustomBucket(5);

        Thread producer = new Thread(()->{
       for(int i =0;i<5;i++) {
           try {
               customBucket.produce(i);
           } catch (InterruptedException e) {
               Thread.currentThread().interrupt();           }
       }

        });

        Thread consumer = new Thread(()->{

            for(int i =0;i<5;i++) {
                try {
                    customBucket.consume();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();                }
            }

        });

        producer.start();
        consumer.start();
    }
}

class CustomBucket{

    private Queue<Integer> queue= new LinkedList<>();
    int capacity;

    CustomBucket(int capacity){
        this.capacity= capacity;
    }

    public synchronized void produce(int i) throws InterruptedException {
        while(queue.size() == capacity){
            wait();
        }
        queue.add(i);
        notifyAll();
    }

    public synchronized int consume() throws InterruptedException {
        while(queue.isEmpty()){
            wait();
        }
        int i = queue.poll();
        System.out.println("i is"+i);
        notifyAll();
        return i;
    }

}
