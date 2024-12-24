package com.hoang.notification.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopPointcut {

    @Pointcut("execution(* com.hoang.notification.service.*.*(..))")
    public void forServicePackage() {}

    @Pointcut("execution(* com.hoang.notification.controller.*.*(..))")
    public void forControllerPackage() {}

    @Pointcut("execution(* com.hoang.notification.service.client.*.*(..))")
    public void forFeignClientPackage() {}

    @Pointcut("execution(* com.hoang.notification.dto.*.set*(..))")
    public void forDtoSetter() {}

    @Pointcut("execution(* com.hoang.notification.dto.*.get*(..))")
    public void forDtoGetter() {}

    @Pointcut("forServicePackage() || forControllerPackage() " +
            "|| forFeignClientPackage() " +
            "|| forDtoSetter() || forDtoGetter()")
    public void forOperation() {}
}
