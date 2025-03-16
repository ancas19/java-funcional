package co.com.reactiveprograming.sec10;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec05GroupedFlux {
    private static final Logger log= LoggerFactory.getLogger(Lec05GroupedFlux.class);

    public static void main(String[] args) {
        Flux.range(1,30)
                .delayElements(Duration.ofSeconds(1))
                .startWith(1)
                .groupBy(i->i%2)
                .flatMap(groupedFlux->processEvents(groupedFlux))
                //.flatMap(group -> group.collectList().map(list -> "Value " + group.key() + ": " + list))
                .subscribe(i->log.info("{}",i));

        Util.sleepSeconds(60);
    }

    private static Mono<Void> processEvents(GroupedFlux<Integer, Integer> flux){
        log.info("Received flux for {}",flux.key());
        return flux
                .doOnNext(i->log.info("key {}, item {}",flux.key(),i))
                .doOnComplete(()->log.info("{} completed",flux.key()))
                .then();
    }
}
