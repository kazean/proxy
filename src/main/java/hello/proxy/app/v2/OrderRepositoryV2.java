package hello.proxy.app.v2;

public class OrderRepositoryV2 {

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
