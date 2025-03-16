package co.com.reactiveprograming.sec03;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;

public class Lec10FluxEmptyError {
    public static void main(String[] args) {
            Flux.empty()
                .log()
                .subscribe(Util.subscriber());
            Flux.error(new RuntimeException("Something went wrong"))
                    .log()
                    .subscribe(Util.subscriber());
    }
}
