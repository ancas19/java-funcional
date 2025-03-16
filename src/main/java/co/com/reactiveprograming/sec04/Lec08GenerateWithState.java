package co.com.reactiveprograming.sec04;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class Lec08GenerateWithState {
    public static void main(String[] args) {
        Flux.generate(
                ()->false,
                (counter,sink)->{
                    String country= Util.faker().country().name();
                    sink.next(country);

                    if (country.equalsIgnoreCase("Canada")) {
                        sink.complete();
                        return true;
                    }
                    return counter;
                }
        )
                .subscribe(Util.subscriber());
    }
}
