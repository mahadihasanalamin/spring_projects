package org.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* org.example.ShoppingCart.checkout(..))")
    public void beforeLogger(JoinPoint jp){
        System.out.println(jp.getSignature());
        String arg = jp.getArgs()[0].toString();
        System.out.println("Cart Status: "+arg);
        System.out.println("Before Logger");
    }
    @After("execution(* org.example.ShoppingCart.checkout(..))")
    public void afterLogger(){
        System.out.println("After Logger");
    }

    @AfterReturning(value = "execution(* org.example.ShoppingCart.quantity())",returning = "retVal")
    public void afterReturning(int retVal){
//        System.out.println(jp.getTarget());
        System.out.println("Returning Value: "+retVal);
    }

    //or
//    @Pointcut("execution(* org.example.ShoppingCart.quantity())")
//    public void returnVal(){
//
//    }
//    @AfterReturning(pointcut = "returnVal()",returning = "retVal")
//    public void afterReturning(int retVal){
////        System.out.println(jp.getTarget());
//        System.out.println("Returning Value: "+retVal);
//    }
}
