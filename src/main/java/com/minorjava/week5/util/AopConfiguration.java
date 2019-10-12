package com.minorjava.week5.util;

import com.minorjava.week5.model.AccountStatus;
import com.minorjava.week5.model.BankAccount;
import com.minorjava.week5.repository.BankAccountRepository;
import com.minorjava.week5.service.BankAccountService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@Aspect
public class AopConfiguration {
//
//    @Autowired
//    BankAccountRepository bankAccountRepository;
//    BankAccountService bankAccountService;

    @Pointcut(
            "execution(public String com.minorjava.week5.BankAccountService.findAll(..))"
    )public void monitor() { }

    public MyPerformanceMonitorInterceptor myPerformanceMonitorInterceptor() {
        return new MyPerformanceMonitorInterceptor(true);
    }

    public Advisor performanceMonitorAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("com.minorjava.week5.AopConfiguration.monitor()");
        return new DefaultPointcutAdvisor(pointcut, myPerformanceMonitorInterceptor());
    }
}
