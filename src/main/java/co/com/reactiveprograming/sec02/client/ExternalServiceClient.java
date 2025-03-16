package co.com.reactiveprograming.sec02.client;

import co.com.reactiveprograming.common.AbstractHttpClass;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClass {
    public Mono<String> getName(Integer productId) {
        return this.client.get()
                .uri("/demo01/product/" + productId)
                .responseContent()
                .asString()
                .next();
    }
}
