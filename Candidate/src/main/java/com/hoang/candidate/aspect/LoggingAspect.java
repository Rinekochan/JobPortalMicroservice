package com.hoang.candidate.aspect;

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

    @Around("com.hoang.candidate.aspect.AopPointcut.forOperation()")
    public Object aroundService(ProceedingJoinPoint joinPoint) throws Throwable {

        String method = joinPoint.getSignature().toShortString();
        logger.info("Executing on method: {}", method);

        Object result = null;

        long begin = System.currentTimeMillis();

        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            logger.error(e.getMessage());
        }

        long end = System.currentTimeMillis();

        logger.info("Finishing method: {}", method);
        logger.info("Method execution time: {} seconds", (end - begin) / 1000.0);

        return result;
    }
}
