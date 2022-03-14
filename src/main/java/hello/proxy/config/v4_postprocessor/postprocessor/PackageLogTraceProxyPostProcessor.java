package hello.proxy.config.v4_postprocessor.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Slf4j
public class PackageLogTraceProxyPostProcessor implements BeanPostProcessor {
    String basePackageName;
    Advisor advisor;

    public PackageLogTraceProxyPostProcessor(String basePackageName, Advisor advisor) {
        this.basePackageName = basePackageName;
        this.advisor = advisor;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("bean={} beanName={}", bean, beanName);

        String packageName = bean.getClass().getPackageName();
        if (!packageName.startsWith(basePackageName)) {
            return bean;
        }

        ProxyFactory proxyFactory = new ProxyFactory(bean);
        proxyFactory.addAdvisor(advisor);
        Object proxy = proxyFactory.getProxy();

        log.info("target={} proxy={}", bean.getClass(), proxy.getClass());
        return proxy;
    }
}
