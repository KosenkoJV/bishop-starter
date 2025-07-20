package com.bishop.audit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

// AOP компонент для перехвата вызовов методов
@Aspect
@Component
public class AuditAspect {

    // Перехватывает вызовы всех методов
    @Around("@annotation(com.bishop.audit.WeylandWatchingYou)")
    public Object auditMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Аудит: вызывается метод " + pjp.getSignature().getName());

        Object result = pjp.proceed(); // выполняем сам метод

        System.out.println("Метод завершился успешно.");
        return result;
    }
}
