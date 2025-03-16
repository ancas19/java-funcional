package co.com.reactiveprograming.sec09.assignment;

import co.com.reactiveprograming.common.AbstractHttpClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;


public class ExternalServiceClient extends AbstractHttpClass {

    public Mono<Product> getProduct(Integer productId) {
        return Mono.zip(getName(productId), getReview(productId), getPrice(productId))
                .map(t -> new Product(t.getT1(), t.getT2(), t.getT3()));
    }
    private Mono<String> getName(Integer productId) {
        return get("/demo05/product/" + productId);
    }
    private Mono<String> getReview(Integer productId) {
        return get("/demo05/review/" + productId);
    }
    private Mono<String> getPrice(Integer productId) {
        return get("/demo05/price/" + productId);
    }

    private Mono<String> get(String path) {
        return this.client.get()
                .uri(path)
                .responseContent()
                .asString()
                .next();
    }
}
