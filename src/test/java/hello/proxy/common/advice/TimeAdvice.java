package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("start");
        long startTime = System.currentTimeMillis();
//        Object result = method.invoke(target, args);
        Object result = invocation.proceed();
        log.info("result={}", result);
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}ms", resultTime);
        return result;
    }
}
