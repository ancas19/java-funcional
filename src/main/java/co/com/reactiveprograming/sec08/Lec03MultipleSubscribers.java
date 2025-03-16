package co.com.reactiveprograming.sec08;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec03MultipleSubscribers {
    private static final Logger log = LoggerFactory.getLogger(Lec03MultipleSubscribers.class);

    public static void main(String[] args) {
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
                .limitRate(5)
                .publishOn(Schedulers.boundedElastic())
                .map(   i->timeConsumingTask(i))
                .subscribe(Util.subscriber("sub1"));

        producer
                .take(100)
                .publishOn(Schedulers.boundedElastic())
                .subscribe(Util.subscriber("sub2"));

        Util.sleepSeconds(60);

    }

    private static int timeConsumingTask(Integer i){
        Util.sleepSeconds(1);
        return i;
    }
}
