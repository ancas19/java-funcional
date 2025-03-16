package co.com.reactiveprograming.sec11;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/*
Repeat operator simply resubscribes when it sees complete signal
It does not like error signal.
 */
public class Lec01Repeat {
    public static void main(String[] args) {

        Flux.just(1,2,3)
                .repeat(4)
                .subscribe(Util.subscriber());
        //demo4();

        Util.sleepSeconds(10);
    }

    private static void demo1(){
        getCountryName().repeat(5)
                .subscribe(Util.subscriber());
    }

    private static void demo2(){
        getCountryName().repeat()
                .takeUntil(c->c.equalsIgnoreCase("Colombia"))
                .subscribe(Util.subscriber());
    }

    private static void demo3(){
        AtomicInteger count = new AtomicInteger(0);
        getCountryName().repeat(()->count.getAndIncrement() < 3)
                .subscribe(Util.subscriber());
    }

    private static void demo4(){
        getCountryName().repeatWhen(flux->flux.delayElements(Duration.ofSeconds(2)).take(2))
                .subscribe(Util.subscriber());
    }
    private static Mono<String> getCountryName(){
        return Mono.fromCallable(()->Util.faker().country().name());
    }
}
