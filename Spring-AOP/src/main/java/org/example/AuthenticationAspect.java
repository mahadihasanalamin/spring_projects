package org.example;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthenticationAspect {
    @Pointcut("within(org.example..*)")
    public void authenticatePointCut(){
        System.out.println("Authenticate PointCut");
    }
    @Pointcut("within(org.example..*)")
    public void authorizationPointCut(){
        System.out.println("Authorization PointCut");
    }
    @Before("authenticatePointCut() && authorizationPointCut()")
    public  void authenticate(){
        System.out.println("Authenticating...");
    }
}
