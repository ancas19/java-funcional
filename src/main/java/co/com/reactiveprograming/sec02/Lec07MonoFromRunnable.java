package co.com.reactiveprograming.sec02;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

/**
 *  Emitting empty after some method invocation
 */
public class Lec07MonoFromRunnable {
    private static final Logger log= LoggerFactory.getLogger(Lec07MonoFromRunnable.class);

    public static void main(String[] args) {
        getProductName(2)
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getProductName(int productId) {
        if (productId == 1) {
            return Mono.fromSupplier(()-> Util.faker().commerce().productName());
        }
        return Mono.fromRunnable(()->notifyBussiness(productId));
    }

    private static void notifyBussiness(Integer productId) {
        log.info("Notifying bussiness for product: {}",productId);
    }
}
