package com.hoang.employer.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopPointcut {

    @Pointcut("execution(* com.hoang.employer.service.*.*(..))")
    public void forServicePackage() {}

    @Pointcut("execution(* com.hoang.employer.controller.*.*(..))")
    public void forControllerPackage() {}

    @Pointcut("execution(* com.hoang.employer.entity.*.set*(..))")
    public void forEntitySetter() {}

    @Pointcut("execution(* com.hoang.employer.entity.*.get*(..))")
    public void forEntityGetter() {}

    @Pointcut("execution(* com.hoang.employer.dto.*.set*(..))")
    public void forDtoSetter() {}

    @Pointcut("execution(* com.hoang.employer.dto.*.get*(..))")
    public void forDtoGetter() {}

    @Pointcut("forServicePackage() || forControllerPackage() " +
            "|| forEntitySetter() || forEntityGetter() " +
            "|| forDtoSetter() || forDtoGetter()")
    public void forOperation() {}
}
