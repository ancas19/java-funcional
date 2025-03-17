package co.com.reactiveprograming.sec12;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec04Multicast {

    public static void main(String[] args) {
        demo2();
    }
    private static  void demo1(){
        // Handle through which we would push items
        // onBackPressureBuffer - bounded queque
        Sinks.Many<Object> sink= Sinks.many().multicast().onBackpressureBuffer();

        //Handle through which subscribers will receive items
        Flux<Object> flux=sink.asFlux();
        flux.subscribe(Util.subscriber("Mike"));
        flux.subscribe(Util.subscriber("Two"));

        for (int i = 0; i < 10; i++) {
            sink.tryEmitNext(i);
        }


        flux.subscribe(Util.subscriber("Final"));
        sink.tryEmitNext("new message");
    }

    private static  void demo2(){
        // Handle through which we would push items
        // onBackPressureBuffer - bounded queque
        Sinks.Many<Object> sink= Sinks.many().multicast().onBackpressureBuffer();

        //Handle through which subscribers will receive items
        Flux<Object> flux=sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        Util.sleepSeconds(2);

        flux.subscribe(Util.subscriber("Mike"));
        flux.subscribe(Util.subscriber("Sam"));
        flux.subscribe(Util.subscriber("Jake"));

        sink.tryEmitNext("new message");
    }
}
