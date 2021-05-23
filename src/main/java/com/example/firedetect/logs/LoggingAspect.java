package com.example.firedetect.logs;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@Aspect
@Slf4j
public class LoggingAspect {


    @Pointcut("@annotation(Loggable)")
    public void executeLogging(){
    }


    @Before("executeLogging()")
    public void logMethodCall(JoinPoint joinPoint){
        StringBuilder message = new StringBuilder("Method:");
        message.append(joinPoint.getSignature().getName()).append("!");
        Arrays.stream(joinPoint.getArgs()).forEach(arg->
            message.append("args:").append(arg).append("!")
        );

        log.info(message.toString());
    }
}
