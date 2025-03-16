package co.com.reactiveprograming.sec03;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxJust {
    public static void main(String[] args) {
        Flux.just("a", "b", "c",12,34)
                .subscribe(Util.subscriber());
    }
}
