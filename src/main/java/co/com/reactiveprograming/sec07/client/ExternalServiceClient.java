package co.com.reactiveprograming.sec07.client;

import co.com.reactiveprograming.common.AbstractHttpClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


public class ExternalServiceClient extends AbstractHttpClass {
    private static final Logger log = LoggerFactory.getLogger(ExternalServiceClient.class.getName());
    public Mono<String> getName(Integer productId) {
        return this.client.get()
                .uri("/demo01/product/" + productId)
                .responseContent()
                .asString()
                .doOnNext(v -> log.info("netx: {}", v))
                .next();
               // .publishOn(Schedulers.boundedElastic());
    }

}
