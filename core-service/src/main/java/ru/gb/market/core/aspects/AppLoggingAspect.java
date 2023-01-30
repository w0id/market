package ru.gb.market.core.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gb.market.core.data.SummaryServicesDuration;


@Aspect
@Component
public class AppLoggingAspect {

    @Autowired
    private SummaryServicesDuration summaryServicesDuration;
    @Pointcut("execution(public * ru.gb.market.core.services.*.*())")
    public void servicesNoArgsTrackerPointcut() {
    }

    @Pointcut("execution(public * ru.gb.market.core.services.*.*(..))")
    public void servicesAllArgsTrackerPointcut() {
    }

    @Pointcut("servicesNoArgsTrackerPointcut() || servicesAllArgsTrackerPointcut()")
    public void servicesNoArgsOrAllArgsTrackerPointcut() {
    }


    @Around("servicesNoArgsOrAllArgsTrackerPointcut()")
    public Object servicesNoArgsOrAllArgsTracker(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        summaryServicesDuration.getDuration()
                .entrySet()
                .stream()
                .filter(i -> i.getKey().equals(proceedingJoinPoint.getTarget().getClass().getSimpleName()))
                .findAny()
                .ifPresentOrElse(v -> {
                            v.setValue(v.getValue() + 1);
                }, () -> {
                    summaryServicesDuration.getDuration().put(proceedingJoinPoint.getTarget().getClass().getSimpleName(), duration);
                        });
        return out;
    }
}
