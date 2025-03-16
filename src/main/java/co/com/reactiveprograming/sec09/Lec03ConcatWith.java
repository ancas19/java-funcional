package co.com.reactiveprograming.sec09;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec03ConcatWith {
    private static final Logger log= LoggerFactory.getLogger(Lec03ConcatWith.class);
    public static void main(String[] args) {
        demo3();
        Util.sleepSeconds(5);
    }

    private static void demo1(){
        producer1()
                .concatWithValues(10,12,13)
                .subscribe(Util.subscriber("sub1"));
    }

    private static void demo2(){
        producer1()
                .concatWith(producer2())
                .subscribe(Util.subscriber("sub1"));
    }

    private static void demo3(){
        Flux.concat(producer1(),producer2())
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
}
