package co.com.reactiveprograming.sec07;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec02SubscribeOn {
    private static final Logger log = LoggerFactory.getLogger(Lec02SubscribeOn.class);
    public static void main(String[] args) {

        Flux flux= Flux.create(sink->{
            for (int i=0;i<10;i++){
                log.info("flux send {}",i);
                sink.next(i);
            }
            sink.complete();
        }).doOnNext(v-> log.info("value: {}",v))
        .doFirst(()-> log.info("doFirst 1"))
        .subscribeOn(Schedulers.boundedElastic())
        .doFirst(()-> log.info("doFirst 2"));

       Runnable runnable1=()-> flux.subscribe(Util.subscriber("sub1"));
       Runnable runnable2=()-> flux.subscribe(Util.subscriber("sub2"));

       Thread.ofPlatform().start(runnable1);
       Thread.ofPlatform().start(runnable2);

        Util.sleepSeconds(5);

    }
}
