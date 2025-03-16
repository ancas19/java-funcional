package co.com.reactiveprograming.sec08;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Reactor automatically handles back pressure
 * System.setProperty("reactor.bufferSize.samll", "16");

 */
public class Lec01BackPressureHandling {
    private static final Logger log= LoggerFactory.getLogger(Lec01BackPressureHandling.class);

    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small","16");


        Flux<Integer> producer=Flux.generate(
                ()->1,
                (state,sink)->{
                    log.info("generating {}",state);
                    sink.next(state);
                    return state+1;
                }
        ).cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
                .publishOn(Schedulers.boundedElastic())
                .map(   i->timeConsumingTask(i))
                .subscribe(Util.subscriber("sub1"));

        Util.sleepSeconds(30);

    }

    private static int timeConsumingTask(Integer i){
        Util.sleepSeconds(1);
        return i;
    }
}
