package com.hoang.jobapplication.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopPointcut {

    @Pointcut("execution(* com.hoang.jobapplication.service.*.*(..))")
    public void forServicePackage() {}

    @Pointcut("execution(* com.hoang.jobapplication.controller.*.*(..))")
    public void forControllerPackage() {}

    @Pointcut("execution(* com.hoang.jobapplication.entity.*.set*(..))")
    public void forEntitySetter() {}

    @Pointcut("execution(* com.hoang.jobapplication.entity.*.get*(..))")
    public void forEntityGetter() {}

    @Pointcut("execution(* com.hoang.jobapplication.dto.*.set*(..))")
    public void forDtoSetter() {}

    @Pointcut("execution(* com.hoang.jobapplication.dto.*.get*(..))")
    public void forDtoGetter() {}

    @Pointcut("forServicePackage() || forControllerPackage() " +
            "|| forEntitySetter() || forEntityGetter() " +
            "|| forDtoSetter() || forDtoGetter()")
    public void forOperation() {}
}
