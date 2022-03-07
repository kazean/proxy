package hello.proxy.app.v3;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV3 {

    public void save(String itemId) {
        if ("ex".equals(itemId)) {
            throw new IllegalArgumentException("예외발생!");
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