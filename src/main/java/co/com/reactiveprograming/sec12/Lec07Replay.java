package co.com.reactiveprograming.sec12;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec07Replay {
    public static void main(String[] args) {
        demo1();
    }


    private static  void demo1(){
        // Handle through which we would push items
        // onBackPressureBuffer - bounded queque
        Sinks.Many<Object> sink= Sinks.many().replay().all();

        //Handle through which subscribers will receive items
        Flux<Object> flux=sink.asFlux();
        flux.subscribe(Util.subscriber("Mike"));
        flux.subscribe(Util.subscriber("Two"));

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        Util.sleepSeconds(2);

        flux.subscribe(Util.subscriber("Final"));
        sink.tryEmitNext("new message");
    }
}
