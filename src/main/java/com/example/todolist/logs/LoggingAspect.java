package com.example.todolist.logs;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Класс реализации для Логирование
 * @author a.shynybayev
 * @version 2.0
 */
@Component
@Aspect
@Slf4j
public class LoggingAspect {

    /**
     * Соберет все точки и привязывает его к методу
     */
    @Pointcut("@annotation(Loggable)")
    public void executeLogging(){
    }

    /**
     * Логируется до выполнения метода
     */
    @Before("executeLogging()")
    public void logMethodCall(JoinPoint joinPoint){
        StringBuilder message = new StringBuilder("Method:");
        message.append(joinPoint.getSignature().getName()).append("!");
        Arrays.stream(joinPoint.getArgs()).forEach(arg->
            message.append("args:").append(arg).append("!")
        );

        log.info(message.toString());
    }

//    @AfterReturning(pointcut = "executeLogging()", returning = "returnValue")
//    public void logMethodCall(JoinPoint joinPoint, Object returnValue){
//        StringBuilder message = new StringBuilder("Method: ");
//        message.append(joinPoint.getSignature().getName()).append("!");
//
//        message.append("return" + returnValue);
//
//        log.info(message.toString());
//    }

//    @Around("executeLogging()")
}
