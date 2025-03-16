package co.com.reactiveprograming.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscribe {
    private static final Logger log = LoggerFactory.getLogger(Lec03MonoSubscribe.class);

    public static void main(String[] args) {
        Mono<Integer>mono=Mono.just(1)
                .map(i->i/0)
                ;
        mono.subscribe(
                i->log.info("Received: {}",i),
                err->log.error("Error: {}",err),
                ()->log.info("Completed"),
                subscription -> subscription.request(100)
        );

    }
}
