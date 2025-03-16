package co.com.reactiveprograming.sec09;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

/*
* Calls multiple publisher in a specific order
 */
public class Lec01StartWith {
    private static final Logger log= LoggerFactory.getLogger(Lec01StartWith.class);

    public static void main(String[] args) {
        demo4();
        Util.sleepSeconds(5);
    }

    private static void demo1(){
        producer1()
                .startWith(10,12,13)
                .subscribe(Util.subscriber("sub1"));
    }

    private static void demo2(){
        producer1()
                .startWith(List.of(-2,-1,0))
                .subscribe(Util.subscriber("sub1"));
    }
    private static Flux<Integer> producer1(){
        return Flux.just(1,2,3)
                .doOnSubscribe(s->log.info("subscribing to producer1"))
                .delayElements(Duration.ofMillis(10));
    }

    private static void demo3(){
        producer1()
                .startWith(producer2())
                .subscribe(Util.subscriber("sub1"));
    }

    private static void demo4(){
        producer1()
                .startWith(producer2())
                .startWith(1000)
                .subscribe(Util.subscriber("sub1"));
    }

    private static Flux<Integer> producer2(){
        return Flux.just(52,53,54)
                .doOnSubscribe(s->log.info("subscribing to producer2"))
                .delayElements(Duration.ofMillis(10));
    }
}
