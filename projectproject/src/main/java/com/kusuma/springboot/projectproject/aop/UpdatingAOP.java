package com.kusuma.springboot.projectproject.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class UpdatingAOP {
    private static final Logger logger = LoggerFactory.getLogger(UpdatingAOP.class);

    @Before("execution(* com.kusuma.springboot.projectproject.controller.*.*Update(..))")
    public void executeBeforeTheUpdateOperationIsPerformed(){
        logger.info("\\>>>" + "\"Updating to the database");
    }
}
