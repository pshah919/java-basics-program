package com.app.annotation;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.Arrays;

public class AnnotationProcessor {

    public static void main(String[] args) {
        PaymentProcessor p = new PaymentProcessor();
        try {
            Method method = p.getClass().getMethod("process");
            if (method.isAnnotationPresent(AuditLog.class)) {
                Annotation a = method.getAnnotation(AuditLog.class);
                System.out.println(Arrays.toString(a.getClass().getMethods()));
            }
            p.process();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}


class PaymentProcessor {


    @AuditLog(action = "abc", severity = "high")
    public void process() {
        System.out.println("processing tx");
    }
}

