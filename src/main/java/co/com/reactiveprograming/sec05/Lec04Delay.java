package co.com.reactiveprograming.sec05;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04Delay {
    public static void main(String[] args) {
        Flux.range(1,10)
                .delayElements(Duration.ofSeconds(2))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(25);
    }
}
