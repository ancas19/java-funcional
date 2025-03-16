package co.com.reactiveprograming.sec03;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec09FluxInterval {
    public static void main(String[] args) {
        Flux.interval(Duration.ofMillis(500))
                .map(i->Util.faker().name().firstName())
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }
}
