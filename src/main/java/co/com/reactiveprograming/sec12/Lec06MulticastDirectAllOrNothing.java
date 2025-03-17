package co.com.reactiveprograming.sec12;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class Lec06MulticastDirectAllOrNothing {
    private static  final Logger log= LoggerFactory.getLogger(Lec06MulticastDirectAllOrNothing.class);

    public static void main(String[] args) {
        demo1();
    }

    private static  void demo1(){
        System.setProperty("reactor.bufferSize.small","16");

        // Handle through which we would push items
        // onBackPressureBuffer - bounded queque
        Sinks.Many<Object> sink= Sinks.many().multicast().directAllOrNothing();

        //Handle through which subscribers will receive items
        Flux<Object> flux=sink.asFlux();
        flux.subscribe(Util.subscriber("Mike"));
        flux.delayElements(Duration.ofMillis(500)).subscribe(Util.subscriber("Two"));

        for (int i=1;i<=100;i++){
            var result=sink.tryEmitNext(i);
            log.info("Item {}, result {}",i,result);
        }
    }
}
