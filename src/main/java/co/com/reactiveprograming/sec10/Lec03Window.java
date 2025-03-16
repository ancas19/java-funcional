package co.com.reactiveprograming.sec10;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec03Window {
    public static void main(String[] args) {

        eventStream()
                .window(Duration.ofMillis(1800))
                .flatMap(i->processEvvent(i))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(15);
    }

    private static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(200))
                .map(i->"event-"+(i+1));
    }

    private static Mono<Void> processEvvent(Flux<String> flux){
        return flux.doOnNext(e-> System.out.print("*"))
                .doOnComplete(()->System.out.println())
                .then();
    }
}
