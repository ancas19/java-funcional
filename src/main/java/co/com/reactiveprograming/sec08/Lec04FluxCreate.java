package co.com.reactiveprograming.sec08;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Lec04FluxCreate {
    private static final Logger log = LoggerFactory.getLogger(Lec04FluxCreate.class);

    public static void main(String[] args) {
        Flux<Integer> producer=Flux.create(sink->{
                    for (int i=0;i<=500 && !sink.isCancelled();i++){
                        log.info("flux send {}",i);
                        sink.next(i);
                        Util.sleep(Duration.ofMillis(50));
                    }
                    sink.complete();
                })
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
                .limitRate(10)
                .publishOn(Schedulers.boundedElastic())
                .map(   i->timeConsumingTask(i))
                .subscribe();

        Util.sleepSeconds(60);

    }

    private static int timeConsumingTask(Integer i){
        log.info("Received {}",i);
        Util.sleepSeconds(1);
        return i;
    }
}
