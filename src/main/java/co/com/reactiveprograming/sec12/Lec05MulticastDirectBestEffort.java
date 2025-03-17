package co.com.reactiveprograming.sec12;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class Lec05MulticastDirectBestEffort {
    private static final Logger log= LoggerFactory.getLogger(Lec05MulticastDirectBestEffort.class);

    public static void main(String[] args) {
        demo2();

        Util.sleepSeconds(10);
    }

    /*
    When we have multiple subscribers, if one subscriber is slow,
    we might not be safely deliver messages to all the subscribers / other fast subscribers might not get messages.
     */
    private static  void demo1(){
        System.setProperty("reactor.bufferSize.small","16");

        // Handle through which we would push items
        // onBackPressureBuffer - bounded queque
        Sinks.Many<Object> sink= Sinks.many().multicast().onBackpressureBuffer();

        //Handle through which subscribers will receive items
        Flux<Object> flux=sink.asFlux();
        flux.subscribe(Util.subscriber("Mike"));
        flux.delayElements(Duration.ofMillis(500)).subscribe(Util.subscriber("Two"));

        for (int i=1;i<=100;i++){
            var result=sink.tryEmitNext(i);
            log.info("Item {}, result {}",i,result);
        }
    }

    /*

    directBestEffort - focus on the subscriber and ignore the slow subscriber
     */

    private static  void demo2(){
        System.setProperty("reactor.bufferSize.small","16");

        // Handle through which we would push items
        // onBackPressureBuffer - bounded queque
        Sinks.Many<Object> sink= Sinks.many().multicast().directBestEffort();

        //Handle through which subscribers will receive items
        Flux<Object> flux=sink.asFlux();
        flux.subscribe(Util.subscriber("Mike"));
        flux.onBackpressureBuffer().delayElements(Duration.ofMillis(500)).subscribe(Util.subscriber("Two"));

        for (int i=1;i<=100;i++){
            var result=sink.tryEmitNext(i);
            log.info("Item {}, result {}",i,result);
        }
    }
}
