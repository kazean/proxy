package hello.proxy.concreteproxy.code;

public class ConcreteClient {
    ConcreteLogic concreteLogic;

    public ConcreteClient(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    public void execute(){
        concreteLogic.operator();
    }
}
