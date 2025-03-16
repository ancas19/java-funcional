package co.com.reactiveprograming.sec03;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;

public class Lec02MultipleSubscribes {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        flux
                .filter(i -> i >10)
                .subscribe(Util.subscriber("sub1"));
        flux.filter(i -> i % 2 != 0)
                .map(i -> i +"x")
                .subscribe(Util.subscriber("sub2"));
        flux.filter(i -> i % 2 == 0)
                .map(i -> i * 10)
                .subscribe(Util.subscriber("sub3"));
    }
}
