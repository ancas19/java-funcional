package co.com.reactiveprograming.sec03.client;

import co.com.reactiveprograming.common.AbstractHttpClass;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClass {
    public Flux<String> getNames() {
        return this.client.get()
                .uri("/demo02/name/stream")
                .responseContent()
                .asString();
    }

    public Flux<Integer> getPrices(){
        return this.client
                .get()
                .uri("/demo02/stock/stream")
                .responseContent()
                .asString()
                .map(Integer::parseInt);
    }
}
