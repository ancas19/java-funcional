package co.com.reactiveprograming.sec04;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;

public class Lec07FluxGenerateUtil {
    public static void main(String[] args) {
        demo2();
    }

    private static void demo1(){
        Flux.generate(synchronousSink -> {
            String country= Util.faker().country().name();
            synchronousSink.next(country);
            if (country.equalsIgnoreCase("Canada")) {
                synchronousSink.complete();
            }
        }).subscribe(Util.subscriber());
    }

    private static void demo2(){
        Flux.<String>generate(synchronousSink -> {
            String country= Util.faker().country().name();
            synchronousSink.next(country);
        }).takeUntil(country -> country.equalsIgnoreCase("Canada"))
                .subscribe(Util.subscriber());
    }
}
