package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.beans.BeanInfo;
import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {
    @Test
    void dynamicA(){
        AInterface aImpl = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(aImpl);
        AInterface proxyInstance = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);
        String call = proxyInstance.call();

        log.info("aImpl class={}", aImpl.getClass());
        log.info("proxyInstance class={}", proxyInstance.getClass());

    }

    @Test
    void dynamicB(){
        BInterface bImpl = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(bImpl);
        BInterface proxyInstance = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]{BInterface.class}, handler);
        String call = proxyInstance.call();

        log.info("bImpl class={}", bImpl.getClass());
        log.info("proxyInstance class={}", proxyInstance.getClass());
    }

}
