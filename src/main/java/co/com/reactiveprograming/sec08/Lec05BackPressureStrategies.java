package co.com.reactiveprograming.sec08;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
 * Reactor provides backpressure handling strategies
 * buffer
 * drop
 * latest
 * error
 */
public class Lec05BackPressureStrategies {
    private static final Logger log = LoggerFactory.getLogger(Lec05BackPressureStrategies.class);

    public static void main(String[] args) {
        Flux<Integer> producer=Flux.create(sink->{
                    for (int i=0;i<=500 && !sink.isCancelled();i++){
                        log.info("flux send {}",i);
                        sink.next(i);
                        Util.sleep(Duration.ofMillis(50));
                    }
                    sink.complete();
                })//, FluxSink.OverflowStrategy.BUFFER
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
                //.onBackpressureBuffer()
                //.onBackpressureError()
               // .onBackpressureBuffer(10)
                //.onBackpressureDrop()
                .onBackpressureLatest()
                .log()
                .limitRate(1)
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
