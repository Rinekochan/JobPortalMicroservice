package com.hoang.user.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopPointcut {

    @Pointcut("execution(* com.hoang.user.service.*.*(..))")
    public void forServicePackage() {}

    @Pointcut("execution(* com.hoang.user.controller.*.*(..))")
    public void forControllerPackage() {}

    @Pointcut("execution(* com.hoang.user.entity.*.set*(..))")
    public void forEntitySetter() {}

    @Pointcut("execution(* com.hoang.user.entity.*.get*(..))")
    public void forEntityGetter() {}

    @Pointcut("execution(* com.hoang.user.dto.*.set*(..))")
    public void forDtoSetter() {}

    @Pointcut("execution(* com.hoang.user.dto.*.get*(..))")
    public void forDtoGetter() {}

    @Pointcut("forServicePackage() || forControllerPackage() " +
            "|| forEntitySetter() || forEntityGetter() " +
            "|| forDtoSetter() || forDtoGetter()")
    public void forOperation() {}
}
