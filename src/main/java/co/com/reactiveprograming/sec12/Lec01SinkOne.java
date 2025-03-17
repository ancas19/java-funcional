package co.com.reactiveprograming.sec12;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class Lec01SinkOne {
    private static final Logger log= LoggerFactory.getLogger(Lec01SinkOne.class);
    public static void main(String[] args) {
        demo3();
    }

    private static void demo1(){
        Sinks.One<Object> sink=Sinks.one();
        Mono<Object> mono=sink.asMono();
        mono.subscribe(Util.subscriber());
        //sink.tryEmitValue("Hello");
        //sink.tryEmitEmpty();
        sink.tryEmitError(new RuntimeException("Opps"));
    }

    private static void demo2(){
        Sinks.One<Object> sink=Sinks.one();
        Mono<Object> mono=sink.asMono();
        mono.subscribe(Util.subscriber("Sam"));
        mono.subscribe(Util.subscriber("Mike"));
        sink.tryEmitValue("Hello");
    }

    private static void demo3(){
        Sinks.One<Object> sink=Sinks.one();
        Mono<Object> mono=sink.asMono();
        mono.subscribe(Util.subscriber("Sam"));

        sink.emitValue("Hi",((signalType, emitResult) -> {
            log.info("SignalType: {}, EmitResult: {}",signalType,emitResult);
            return false;
        }));

        sink.emitValue("Hello",((signalType, emitResult) -> {
            log.info("Hello");
            log.info("SignalType: {}, EmitResult: {}",signalType,emitResult);
            return false;
        }));
    }
}
