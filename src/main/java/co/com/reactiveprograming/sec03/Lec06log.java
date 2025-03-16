package co.com.reactiveprograming.sec03;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;

public class Lec06log {
    public static void main(String[] args) {
        Flux.range(1, 5)
                .log()
                .map(i->Util.faker().name().firstName())
                .log("after map")
                .subscribe(Util.subscriber());

    }
}
