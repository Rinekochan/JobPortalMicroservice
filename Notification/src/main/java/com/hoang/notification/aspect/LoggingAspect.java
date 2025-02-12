package com.hoang.notification.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("com.hoang.notification.aspect.AopPointcut.forOperation()")
    public Object aroundService(ProceedingJoinPoint joinPoint) throws Throwable {

        String method = joinPoint.getSignature().toShortString();
        logger.info("Executing on method: {}", method);

        Object result = null;

        long begin = System.currentTimeMillis();

        try {
            result = joinPoint.proceed();
        } catch (Throwable ex) {
            throw new RuntimeException(
                    "Error in method: " + joinPoint.getSignature()
                            + " with arguments: " + java.util.Arrays.toString(joinPoint.getArgs()), ex);
        } finally {
            long end = System.currentTimeMillis();

            logger.info("Finishing method: {}", method);
            logger.info("Method execution time: {} seconds", (end - begin) / 1000.0);
        }

        return result;
    }
}
