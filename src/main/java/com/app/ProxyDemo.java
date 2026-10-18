package com.app;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyDemo {
    public static void main(String[] args) {
        OrderService realService = new RealOrderService();

        // Create the Proxy instance implementing the OrderService interface dynamically
        OrderService proxyService = (OrderService) Proxy.newProxyInstance(
                OrderService.class.getClassLoader(),
                new Class<?>[]{OrderService.class},
                new TransactionInvocationHandler(realService)
        );

        // Invoking method on proxy triggers TransactionInvocationHandler.invoke()
        proxyService.executeOrder("ASML", 50);
    }
}


// Target Interface & Implementation
interface OrderService {
    void executeOrder(String symbol, int qty);
}

class RealOrderService implements OrderService {
    public void executeOrder(String symbol, int qty) {
        System.out.println("Order executed: " + qty + " shares of " + symbol);
    }
}

// InvocationHandler intercepts every method call at runtime
class TransactionInvocationHandler implements InvocationHandler {

    private final Object target;

    public TransactionInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("[AOP] BEGIN TRANSACTION"); // Pre-processing

        Object result = null;
        try {
            // Execute actual target method using Reflection
            result = method.invoke(target, args);
            System.out.println("[AOP] COMMIT TRANSACTION"); // Post-processing
        } catch (Exception e) {
            System.out.println("[AOP] ROLLBACK TRANSACTION");
            throw e;
        }

        return result;
    }
}

