package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanPostProcessorTest {

    @Test
    void basicConfig(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);
        B b = applicationContext.getBean("beanA", B.class);
        b.helloB();

        Assertions.assertThatThrownBy(() -> applicationContext.getBean(A.class)).isInstanceOf(NoSuchBeanDefinitionException.class);

    }

    @Configuration
    static class BasicConfig {

        @Bean(name = "beanA")
        public A A(){
            return new A();
        }

        @Bean
        public AToBBeanPostProcessor helloPostProcessor(){
            return new AToBBeanPostProcessor();
        }
    }

    @Slf4j
    static class AToBBeanPostProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("beanName={}", beanName);
            if(bean instanceof A){
                return new B();
            }

            return bean;
        }
    }

    @Slf4j
    static class A{
        public void helloA(){
            log.info("hello A");
        }
    }

    @Slf4j
    static class B{
        public void helloB(){
            log.info("hello B");
        }
    }
}
