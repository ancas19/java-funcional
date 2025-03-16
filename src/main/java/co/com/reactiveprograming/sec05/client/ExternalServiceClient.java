package co.com.reactiveprograming.sec05.client;

import co.com.reactiveprograming.common.AbstractHttpClass;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ExternalServiceClient extends AbstractHttpClass {
    public Mono<String> getProductName(Integer productId) {
        String path = "/demo03/product/" + productId;
        String timeoutPath = "/demo03/timeout-fallback/product/" + productId;
        String emptyPath = "/demo03/empty-fallback/product/" + productId;
        return getName(path)
                .timeout(Duration.ofSeconds(2),getName(timeoutPath))
                .switchIfEmpty(getName(emptyPath));

    }
    private Mono<String> getName(String path) {
        return this.client.get()
                .uri(path)
                .responseContent()
                .asString()
                .next();
    }
}
