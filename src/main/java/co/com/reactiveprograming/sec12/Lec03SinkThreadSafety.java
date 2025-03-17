package co.com.reactiveprograming.sec12;


import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Lec03SinkThreadSafety {
    private static  final Logger log= LoggerFactory.getLogger(Lec03SinkThreadSafety.class);

    public static void main(String[] args) {
        demo2();
    }

    private static  void demo1(){
        // Handle through which we would push items
        // onBackPressureBuffer - unbounded queque
        Sinks.Many<Object> sink= Sinks.many().unicast().onBackpressureBuffer();

        //Handle through which subscribers will receive items
        Flux<Object> flux=sink.asFlux();

       var list=new ArrayList<>();
        flux.subscribe(list::add);

        for (int i=0;i<1000;i++){
            int j=i;
            CompletableFuture.runAsync(()->{
                sink.tryEmitNext(j);
            });
        }

        Util.sleepSeconds(2);

        log.info("List size: {}",list.size());
    }

    private static  void demo2(){
        // Handle through which we would push items
        // onBackPressureBuffer - unbounded queque
        Sinks.Many<Object> sink= Sinks.many().unicast().onBackpressureBuffer();

        //Handle through which subscribers will receive items
        Flux<Object> flux=sink.asFlux();

        var list=new ArrayList<>();
        flux.subscribe(list::add);

        for (int i=0;i<1000;i++){
            int j=i;
            CompletableFuture.runAsync(()->{
                sink.emitNext(j,((signalType, emitResult) -> {
                    return Sinks.EmitResult.FAIL_NON_SERIALIZED.equals(emitResult);
                }));
            });
        }

        Util.sleepSeconds(2);

        log.info("List size: {}",list.size());
    }
}
