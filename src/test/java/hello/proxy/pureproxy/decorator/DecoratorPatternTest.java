package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.*;
import org.junit.jupiter.api.Test;

public class DecoratorPatternTest {

    @Test
    void noDecorator(){
        Component component = new RealCompoent();
        DecoratorPatternClient client = new DecoratorPatternClient(component);
        client.execute();
    }

    @Test
    void decorator1(){
        Component realComponent = new RealCompoent();
        MessageDecorator messageDecorator = new MessageDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);
        client.execute();
    }

    @Test
    void decorator2(){
        Component realComponent = new RealCompoent();
        MessageDecorator messageDecorator = new MessageDecorator(realComponent);
        TimeDecorator timeDecorator = new TimeDecorator(messageDecorator);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);

        client.execute();
    }
}
