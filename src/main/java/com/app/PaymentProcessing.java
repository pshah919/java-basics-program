package com.app;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class PaymentProcessing {

    // 1. Fire-and-Forget async task (returns void)
    @Async("paymentTaskExecutor")
    public void sendPaymentNotification(String transactionId) {
        System.out.println("Sending notification on thread: " + Thread.currentThread().getName());
        // Business logic here...
    }

    // 2. Asynchronous task returning a result via CompletableFuture
    @Async("paymentTaskExecutor")
    public CompletableFuture<String> processAuthorization(String cardDetails) {
        System.out.println("Authorizing on thread: " + Thread.currentThread().getName());

        // Simulate authorization process
        String authResult = "AUTH_SUCCESS_" + System.currentTimeMillis();

        return CompletableFuture.completedFuture(authResult);
    }
}