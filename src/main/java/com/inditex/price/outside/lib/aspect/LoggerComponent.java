package com.inditex.price.outside.lib.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
@ConditionalOnProperty(name = "aspect.enabled", havingValue = "true")
public class LoggerComponent {

  private static final String POINTCUT = "execution(* com.inditex..*(..))))";

  @Around(POINTCUT)
  @SneakyThrows
  public Object logAroundExec(ProceedingJoinPoint pjp) {
    final StopWatch stopWatch = new StopWatch();
    log.info("before {}", constructLogMsg(pjp));
    stopWatch.start();
    Object proceed = pjp.proceed();
    stopWatch.stop();
    log.info("after {} with result: {} taking {} ms",
        constructLogMsg(pjp), Objects.isNull(proceed) ? "" : proceed.toString(),
        stopWatch.getTotalTimeMillis());
    return proceed;
  }

  @AfterThrowing(pointcut = POINTCUT, throwing = "e")
  public void logAfterException(JoinPoint jp, Exception e) {
    log.error("Exception during: {} with ex: {}", constructLogMsg(jp), e.toString());
  }

  private String constructLogMsg(JoinPoint jp) {
    String args = Arrays.stream(jp.getArgs()).map(String::valueOf)
        .collect(Collectors.joining(",", "[", "]"));
    Method method = ((MethodSignature) jp.getSignature()).getMethod();
    return "@" + method.getName() + ":" + args;
  }

}
