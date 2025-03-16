package co.com.reactiveprograming.sec06.assignment;

import co.com.reactiveprograming.common.AbstractHttpClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.Objects;

public class ExternalServiceClient extends AbstractHttpClass {
    private static  final Logger log= LoggerFactory.getLogger(ExternalServiceClient.class);
    private Flux<Order> orderFlux;
    public  Flux<Order> orderStream(){
        if(Objects.isNull(orderFlux)) {
            orderFlux = getorderStream();
        }
        return orderFlux;
    }
    private Flux<Order> getorderStream() {
        return this.client.get()
                .uri("/demo04/orders/stream")
                .responseContent()
                .asString()
                .map(this::parse)
                .doOnNext(order->log.info("Received order {}",order))
                .publish()
                .autoConnect(0);
    }

    private Order parse(String message) {
        log.info("Parsing message {}",message);
        String[] parts = message.split(",");
        return new Order(parts[1].split("=")[1], Integer.parseInt(parts[2].split("=")[1]), Integer.parseInt(parts[3].split("=")[1]));
    }
}
