package kr.jay.common;

import javax.validation.constraints.NotNull;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * LoggingAspect
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/07
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

	private final LoggingProducer loggingProducer;

	@Before("execution(* kr.jay.*.adapter.in.web.*.*(..))")
	public void beforeMethodsExecution(final JoinPoint joinPoint) {
		final String methodName = joinPoint.getSignature().getName();
		log.info("Before executing method: {}", methodName);
		loggingProducer.sendMessage("logging", "Before executing method: " + methodName);
	}
}
