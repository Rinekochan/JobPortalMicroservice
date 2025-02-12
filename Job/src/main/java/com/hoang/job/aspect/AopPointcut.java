package com.hoang.job.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopPointcut {

    @Pointcut("execution(* com.hoang.job.service.*.*(..))")
    public void forServicePackage() {}

    @Pointcut("execution(* com.hoang.job.controller.*.*(..))")
    public void forControllerPackage() {}

    @Pointcut("execution(* com.hoang.job.entity.*.set*(..))")
    public void forEntitySetter() {}

    @Pointcut("execution(* com.hoang.job.entity.*.get*(..))")
    public void forEntityGetter() {}

    @Pointcut("execution(* com.hoang.job.dto.*.set*(..))")
    public void forDtoSetter() {}

    @Pointcut("execution(* com.hoang.job.dto.*.get*(..))")
    public void forDtoGetter() {}

    @Pointcut("forServicePackage() || forControllerPackage() " +
            "|| forEntitySetter() || forEntityGetter() " +
            "|| forDtoSetter() || forDtoGetter()")
    public void forOperation() {}
}
