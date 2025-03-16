package co.com.reactiveprograming.sec05;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * Similar to error handling.
 * Handling Empty
 */
public class Lec08SwitchifEmpty {
    private static final Logger log= LoggerFactory.getLogger(Lec08SwitchifEmpty.class);

    public static void main(String[] args) {
        Flux.range(1,10)
                .filter(i->i>11)
                .switchIfEmpty(fallback())
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> fallback() {
        return Flux.range(100,3);
    }
}
