package co.com.reactiveprograming.sec06;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
    Hot - 1 data prducer for all the subscribers
    share => publish().refCount(1)
    It needs 1 min subscriber to emit data.
    It stops when there is 0 subscriber
    Re-subscription - It starts again where is a new subscriber
    To have min 2 subscribers, use publish().refCount(2)
 */
public class Lec02HotPublisher {
    private static final Logger log = LoggerFactory.getLogger(Lec02HotPublisher.class);

    public static void main(String[] args) {
        /*Flux<String> movieFlux=movieStream()
                .share();*/

        Flux<String> movieFlux=movieStream()
                .publish()
                .refCount(2);

        Util.sleepSeconds(2);
        movieFlux
                .take(5)
                .subscribe(Util.subscriber("sam"));
        Util.sleepSeconds(3);
        movieFlux
                .take(3)
                .subscribe(Util.subscriber("julie"));


        Util.sleepSeconds(20);
    }

    //Movie theater
    private static Flux<String> movieStream(){
        return Flux.generate(
                ()->{
                    log.info("Received the request");
                    return 1;
                },
                (state,sink)->{
                    String movieScene="Movie scene "+state;
                    log.info("playing {}",movieScene);
                    sink.next(movieScene);
                    return ++state;
                }
        ).take(11)
                .delayElements(Duration.ofSeconds(2))
                .cast(String.class);
    }
}
