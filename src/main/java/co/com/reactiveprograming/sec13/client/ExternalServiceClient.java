package co.com.reactiveprograming.sec13.client;

import co.com.reactiveprograming.common.AbstractHttpClass;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClass {

    public Mono<String> getBook(){
        return this.client.get()
                .uri("/demo07/book")
                .responseContent()
                .asString()
                .startWith(RateLimiter.limitCalls())
                .contextWrite(UserService.userCategoryContext())
                .next();
    }
}
