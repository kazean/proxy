package hello.proxy.app.v1;

public class OrderRepositoryV1Impl implements OrderRepositoryV1{
    @Override
    public void save(String itemId) {
        if("ex".equals(itemId)){
            throw new IllegalArgumentException("μμΈλ°μ!");
        }
        sleep(1000);
    }

    private void sleep(int mills) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
