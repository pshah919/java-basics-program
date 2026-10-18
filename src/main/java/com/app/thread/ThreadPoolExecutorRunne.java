package com.app.thread;

import com.app.PaymentProcessing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.CompletableFuture;
@SpringBootApplication
@ComponentScan("com.app")
@EnableAsync // Required to enable @Async processing
public class ThreadPoolExecutorRunne {

        public static void main(String[] args) {
            SpringApplication.run(ThreadPoolExecutorRunne.class, args);
        }

        @Bean
        public CommandLineRunner run(PaymentProcessing paymentService) {
            return args -> {
                System.out.println("Main thread running on: " + Thread.currentThread().getName());

                // 1. Fire-and-Forget Call
                paymentService.sendPaymentNotification("TXN_99812");

                // 2. Async Call returning a CompletableFuture
                CompletableFuture<String> futureResult = paymentService.processAuthorization("4111-XXXX-XXXX-1111");

                // Non-blocking callback handling
                futureResult.thenAccept(result ->
                        System.out.println("Callback received: " + result + " on thread: " + Thread.currentThread().getName())
                );

                System.out.println("Main thread continues executing without waiting!");
            };
        }
    }

