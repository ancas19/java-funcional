package co.com.reactiveprograming.sec06;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
    Almost same as publish().refCount(1).
    - does NOT stop when subscriber cancel. So it will start producing even for 0 subscribers once it started
    - make it real hot- publish().autoConnect(0)
 */
public class Lec03HotPublisherAutoConnect {
    private static final Logger log = LoggerFactory.getLogger(Lec03HotPublisherAutoConnect.class);

    public static void main(String[] args) {
        /*Flux<String> movieFlux=movieStream()
                .share();*/

        Flux<String> movieFlux=movieStream()
                .publish()
                .autoConnect(0);

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
