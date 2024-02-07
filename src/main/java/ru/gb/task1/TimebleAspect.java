package ru.gb.task1;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TimebleAspect {
    @Pointcut("within(ru.gb.task1.*)")
    public void beansAnnotatedWith() {
    }

    @Pointcut("@annotation(ru.gb.task1.Timetable)")
    public void methodsAnnotatedWith() {
    }

    @Around("beansAnnotatedWith() || methodsAnnotatedWith()")
    public void timebleAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Long start = System.currentTimeMillis();
        try {
            joinPoint.proceed();
            Long res = System.currentTimeMillis() - start;
            log.info("Название класса: {}, название метода: {}, время выполнения: {} милисекунд",
                    joinPoint.getTarget().getClass(),
                    joinPoint.getSignature().getName(), res);
        } catch (Throwable e) {
            log.info(e.getMessage());
            throw e;
        }

    }

}
