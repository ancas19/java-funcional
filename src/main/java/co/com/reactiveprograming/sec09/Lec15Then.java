package co.com.reactiveprograming.sec09;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

/*
"Then" could be helpul when we are not interested in the result of a publisher/
we need to have sequential execution of asynchronous tasks
 */
public class Lec15Then {
    private static final Logger log = LoggerFactory.getLogger(Lec15Then.class.getName());

    public static void main(String[] args) {

        List<String> records=List.of("a", "b", "c");
        saveRecords(records)
                .then(sendNotification(records))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(6);
    }

    private static Flux<String> saveRecords(List<String> flux) {

        return Flux.fromIterable(flux)
                .map(s -> {
                    log.info("saving: {}", s);
                    return s;
                })
                .delayElements(Duration.ofMillis(500));
    }


    private static Mono<Void> sendNotification(List<String> flux) {
        return Mono.fromRunnable(()->log.info("All these {} records have been saved", flux));
    }
}
