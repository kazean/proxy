package hello.proxy.advisor;

import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class MultiAdvisorTest {

    @Test
    @DisplayName("여러 프록시")
    void multiAdvisorTest1(){
        ServiceInterface target = new ServiceImpl();

        ProxyFactory factory1 = new ProxyFactory(target);
        factory1.addAdvice(new Advice1());
        ServiceInterface proxy1 = (ServiceInterface) factory1.getProxy();

        ProxyFactory factory2 = new ProxyFactory(proxy1);
        factory2.addAdvice(new Advice2());
        ServiceInterface proxy2 = (ServiceInterface) factory2.getProxy();

        proxy2.save();

    }

    @Test
    @DisplayName("하나의 프록시 여러개 어드바이저")
    void multiAdvisortTest2(){
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new Advice2());
        proxyFactory.addAdvice(new Advice1());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
    }

    static class Advice1 implements MethodInterceptor{
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice1");
            return invocation.proceed();
        }
    }

    static class Advice2 implements MethodInterceptor{
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice2");
            return invocation.proceed();
        }
    }
}
