package co.com.reactiveprograming.sec09;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04ConcatError {
    private static final Logger log= LoggerFactory.getLogger(Lec04ConcatError.class);

    public static void main(String[] args) {
        demo2();
        Util.sleepSeconds(5);
    }

    private static void demo1(){
        producer1()
                .concatWith(producer3())
                .concatWith(producer2())
                .subscribe(Util.subscriber("sub1"));
    }

    private static void demo2(){
     Flux.concatDelayError(producer1(),producer3(),producer2())
             .subscribe(Util.subscriber("sub1"));
    }

    private static Flux<Integer> producer1(){
        return Flux.just(1,2,3)
                .doOnSubscribe(s->log.info("subscribing to producer1"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer2(){
        return Flux.just(52,53,54)
                .doOnSubscribe(s->log.info("subscribing to producer2"))
                .delayElements(Duration.ofMillis(10));
    }
    private static Flux<Integer> producer3(){
        return Flux.error(new RuntimeException("Producer 3 error"));
    }
}
