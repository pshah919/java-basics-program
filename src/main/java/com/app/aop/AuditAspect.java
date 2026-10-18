package com.app.aop;

import com.app.annotation.AuditLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect{

    @Around("@annotation(auditLog)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint, AuditLog auditLog){
        long startTime = System.currentTimeMillis();
        System.out.println("[AOP] Action: " + auditLog.action());
        try {
            // 3. JOIN POINT EXECUTION: Proceeding to run the target method
            Object result = joinPoint.proceed();

            // 2. ADVICE (Part 2 - After Returning Logic): Code executing AFTER success
            long duration = System.currentTimeMillis() - startTime;
            System.out.println("[AOP] Executed in " + duration + " ms");
            return result;
        } catch (Throwable ex) {
            // 2. ADVICE (Part 3 - After Throwing Logic): Code executing on Exception
        }
        return null;
    }
}
