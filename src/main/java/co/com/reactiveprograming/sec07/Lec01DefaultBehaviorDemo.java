package co.com.reactiveprograming.sec07;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/*
By default, the current thread is doing all the work.
 */
public class Lec01DefaultBehaviorDemo {
    private static final Logger log = LoggerFactory.getLogger(Lec01DefaultBehaviorDemo.class);

    public static void main(String[] args) {
        Flux flux= Flux.create(sink->{
            for (int i=0;i<10;i++){
                log.info("flux send {}",i);
                sink.next(i);
            }
            sink.complete();
        }).doOnNext(v-> log.info("value: {}",v));

        Runnable runnable=()-> flux.subscribe(Util.subscriber("sub1"));
        Thread.ofPlatform().start(runnable);
    }
}
