package co.com.reactiveprograming.sec11.client;

import co.com.reactiveprograming.common.AbstractHttpClass;
import co.com.reactiveprograming.sec09.assignment.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClientResponse;


public class ExternalServiceClient extends AbstractHttpClass {


    public Mono<String> getName(Integer productId) {
        return get("/demo06/product/" + productId);
    }
    public Mono<String> getCountry() {
        return get("/demo06/country");
    }
    private Mono<String> get(String path) {
        return this.client.get()
                .uri(path)
                .response(this::toResponse)
                .next();
    }

    private Flux<String> toResponse(HttpClientResponse httpClientResponse, ByteBufFlux byteBufFlux) {
        return switch (httpClientResponse.status().code()){
            case 200->byteBufFlux.asString();
            case 400->Flux.error(new ClientError());
            default->Flux.error(new ServerError());
        };
    }
}
