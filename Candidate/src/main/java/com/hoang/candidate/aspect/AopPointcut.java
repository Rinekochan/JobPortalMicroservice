package com.hoang.candidate.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopPointcut {

    @Pointcut("execution(* com.hoang.candidate.service.*.*(..))")
    public void forServicePackage() {}

    @Pointcut("execution(* com.hoang.candidate.controller.*.*(..))")
    public void forControllerPackage() {}

    @Pointcut("execution(* com.hoang.candidate.entity.*.set*(..))")
    public void forEntitySetter() {}

    @Pointcut("execution(* com.hoang.candidate.entity.*.get*(..))")
    public void forEntityGetter() {}

    @Pointcut("execution(* com.hoang.candidate.dto.*.set*(..))")
    public void forDtoSetter() {}

    @Pointcut("execution(* com.hoang.candidate.dto.*.get*(..))")
    public void forDtoGetter() {}

    @Pointcut("forServicePackage() || forControllerPackage() " +
            "|| forEntitySetter() || forEntityGetter() " +
            "|| forDtoSetter() || forDtoGetter()")
    public void forOperation() {}
}
