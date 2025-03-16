package co.com.reactiveprograming.sec07;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*
    publish on for downstream
    subscribe on for upstream
 */
public class Lec07OublishOnSubscribeOn {
    private static final Logger log = LoggerFactory.getLogger(Lec07OublishOnSubscribeOn.class);

    public static void main(String[] args) {

        Flux flux= Flux.create(sink->{
                    for (int i=0;i<10;i++){
                        log.info("Generating {}",i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .publishOn(Schedulers.parallel())
                .doOnNext(v-> log.info("value: {}",v))
                .doFirst(()-> log.info("doFirst-1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(()-> log.info("doFirst 2"));

        Runnable runnable1=()-> flux.subscribe(Util.subscriber("sub1"));
        Thread.ofPlatform().start(runnable1);
        Thread.ofPlatform().start(runnable1);

        Util.sleepSeconds(5);
    }
}
