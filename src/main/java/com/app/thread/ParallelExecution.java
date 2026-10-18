package com.app.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.lang.Thread.sleep;

public class ParallelExecution {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Fetched User Details");
            return "User Profile";
        });

        // Task 2
        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            try {
                sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Fetched User Orders");
            return "Order History";
        });

        // Task 3
        CompletableFuture<String> task3 = CompletableFuture.supplyAsync(() -> {
            try {
                sleep(800);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Fetched User Recommendations");
            return "Product Recommendations";
        });

        // Execute all in parallel and wait for all of them to finish
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2, task3);
/*allTasks.join();
System.out.println(task1.get());*/
        // Retrieve results once all complete
        allTasks.thenRun(() -> {
            try {
                String profile = task1.get();
                String orders = task2.get();
                String recs = task3.get();

                System.out.println("Dashboard Data Ready: " + profile + " | " + orders + " | " + recs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join();
    }
}
