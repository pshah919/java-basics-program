package com.app.thread;

import java.util.concurrent.*;

public class CompletableFutureEx {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();

        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            System.out.println(i);
            service.submit(() -> {
                Thread.sleep(2000);
                return "TaskId " + taskId;
            });
        }

        // 1. Supply an asynchronous task (e.g., fetching data from a database/API)
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                    simulateDelay(1000); // Simulate network I/O
                    return "user_priti_shah";
                }, service)
                // 2. Chain a non-blocking transformation once the result is ready
                .thenApply(username -> {
                    return "Formatted User: " + username.toUpperCase();
                })
                // 3. Handle any exceptions cleanly if something fails upstream
                .exceptionally(ex -> {
                    System.err.println("Error occurred: " + ex.getMessage());
                    return "Fallback User";
                });

        // Main thread continues executing other work concurrently
        System.out.println("Main thread is free to do other work...");

        // 4. Block and retrieve the final result (or use .thenAccept() for non-blocking callbacks)
        String finalResult = future.get();
        System.out.println("Result received: " + finalResult);
    }

    private static void simulateDelay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
