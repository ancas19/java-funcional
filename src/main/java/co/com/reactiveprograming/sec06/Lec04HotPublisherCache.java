package co.com.reactiveprograming.sec06;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * publish().autoconnect(0) will provide new values to the subscriber
 * replay allows us to cache
 */
public class Lec04HotPublisherCache {
    private static final Logger log = LoggerFactory.getLogger(Lec04HotPublisherCache.class);

    public static void main(String[] args) {
        /*Flux<String> stockFlux=movieStream()
                .share();*/

        Flux<Integer> stockFlux=stockStream()
                .replay()
                .autoConnect(0);

        Util.sleepSeconds(4);
        log.info("Sam joining");
        stockFlux
                .subscribe(Util.subscriber("sam"));
        Util.sleepSeconds(4);
        log.info("Julie joining");
        stockFlux
                .subscribe(Util.subscriber("julie"));


        Util.sleepSeconds(20);
    }

    //Movie theater
    private static Flux<Integer> stockStream(){
        return Flux.generate(sink->sink.next(Util.faker().random().nextInt(10,100)))
                .delayElements(Duration.ofSeconds(3))
                .doOnNext(price->log.info("Emitting price {}",price))
                .cast(Integer.class);
    }
}
