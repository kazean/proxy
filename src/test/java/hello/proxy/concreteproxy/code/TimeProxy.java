package hello.proxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreteLogic{
    private ConcreteLogic realLogic;

    public TimeProxy(ConcreteLogic target) {
        this.realLogic = target;
    }

    @Override
    public String operator() {
        log.info("TimeProxy 실행");
        long start = System.currentTimeMillis();

        String result = realLogic.operator();

        long end = System.currentTimeMillis();
        long resultTime = end - start;
        log.info("TimeProxy 종료 resultTime={}ms", resultTime);
        return result;
    }
}
