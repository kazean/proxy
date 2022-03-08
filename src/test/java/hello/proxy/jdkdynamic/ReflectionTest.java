package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    /**
     * test reflection0()
     * >hello callA(), callB()
     *
     * class Hello
     * callA() : String
     * callB() : String
     */
    @Test
    void reflection0(){
        //공롱로직1
        log.info("start");
        Hello hello = new Hello();
        String result1 = hello.callA();
        log.info("result1={}", result1);

        //공통로직2
        log.info("start");
        String result2 = hello.callB();
        log.info("result2={}", result2);

    }

    /**
     * 공통로직1과 공통로직2를 하나로
     */
    @Test
    void reflection1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Hello target = new Hello();
        Class<?> clazz = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
        log.info("start");
        Method callA = clazz.getMethod("callA");
        Object resultA = callA.invoke(target);
        log.info("resultA={}", resultA);

        log.info("start");
        Method callB = clazz.getMethod("callB");
        Object resultB = callB.invoke(target);
        log.info("resultB={}", resultB);
    }

    /**
     * reflection2 : reflection1 + dynamicCall(Method m, Object target)
     */
    @Test
    void reflection2() throws  Exception{
        Hello target = new Hello();
        Class<?> clazz = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Method callA = clazz.getMethod("callA");
        dynamicCall(callA, target);
        Method callB = clazz.getMethod("callB");
        dynamicCall(callB, target);
    }

    private void dynamicCall(Method method, Object target) throws InvocationTargetException, IllegalAccessException {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}", result);
    }

    @Slf4j
    static class Hello{
        public String callA(){
            log.info("callA");
            return "A";
        }

        public String callB(){
            log.info("callB");
            return "B";
        }
    }
}
