package ru.gb.task2;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Slf4j
@Aspect
@Component
public class RecoverExceptionAspect {
    @Pointcut("@annotation(ru.gb.task2.RecoverException)")
    public void methodsAnnotatedWith() {
    }

    @Around("methodsAnnotatedWith()")
    public Object RecoverExceptionAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        RecoverException annotation = methodSignature.getMethod().getAnnotation(RecoverException.class);
        Class<? extends RuntimeException>[] classes = annotation.noRecoverFor();

        final Object proceed;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable ex) {
            if (!Arrays.asList(classes).contains(ex)) {
                log.info("Возвращаемое значение: null");
                return null;
            } else {
                throw ex;
            }
        }
        log.info("Возвращаемое значение: {}", proceed.toString());
        return proceed;
    }
}
