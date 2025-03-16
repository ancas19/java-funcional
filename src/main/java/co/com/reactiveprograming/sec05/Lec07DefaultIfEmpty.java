package co.com.reactiveprograming.sec05;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
    Similar to error handling.
    Handling Empty
 */
public class Lec07DefaultIfEmpty {
    public static void main(String[] args) {
        Mono.just("Castrico")
                .defaultIfEmpty("default")
                .subscribe(Util.subscriber());

        Flux.range(1,10)
                .filter(i->i>11)
                .defaultIfEmpty(12)
                .subscribe(Util.subscriber());
    }
}
