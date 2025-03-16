package co.com.reactiveprograming.sec03;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxRange {
    public static void main(String[] args) {
        Flux.range(3, 100)
                .filter(i -> i %15==0)
                .subscribe(Util.subscriber());

        Flux.range(1,10)
                .map(i->Util.faker().name().firstName())
                .subscribe(Util.subscriber());

        Flux.range(13, 17)
                .subscribe(Util.subscriber());
    }
}
